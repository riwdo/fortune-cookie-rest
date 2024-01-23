package com.good.fortunecookierestregressiontests;

import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testcontainers.containers.GenericContainer;
import org.testcontainers.containers.Network;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.shaded.com.fasterxml.jackson.databind.ObjectMapper;

public abstract class AbstractRegressionTest {

  public static ObjectMapper objectMapper = new ObjectMapper();

  public static PostgreSQLContainer<?> postgreSQLContainer;
  public static GenericContainer<?> fortuneCookieRestApplication;
  public static GenericContainer<?> paymentApplication;

  public static Network network;

  static {
    network = Network.newNetwork();

    postgreSQLContainer = createPostgresContainer();
    fortuneCookieRestApplication = createFortuneCookieRestApplication();
    paymentApplication = createPaymentApplication();
    postgreSQLContainer.start();
    paymentApplication.start();

    fortuneCookieRestApplication.withEnv(
        Map.of(
            "SPRING_DATASOURCE_URL",
            "jdbc:postgresql://postgres:5432/test",
            "SPRING_DATASOURCE_USERNAME",
            postgreSQLContainer.getUsername(),
            "SPRING_DATASOURCE_PASSWORD",
            postgreSQLContainer.getPassword(),
            "SPRING_FLYWAY_USER",
            postgreSQLContainer.getUsername(),
            "SPRING_FLYWAY_PASSWORD",
            postgreSQLContainer.getPassword(),
            "API_PAYMENT_BASE-URL",
            "http://payment:8081"));
    fortuneCookieRestApplication.start();
  }

  private static GenericContainer<?> createFortuneCookieRestApplication() {
    return new GenericContainer<>("fortune-cookie-rest-app:latest")
        .withExposedPorts(8080)
        .withNetwork(network)
        .withNetworkAliases("fortune-cookie-rest")
        .dependsOn(postgreSQLContainer)
        .withLogConsumer(
            outputFrame ->
                logContainerMsg(
                    outputFrame.getUtf8String(),
                    "APP:",
                    LoggerFactory.getLogger("CONTAINER_LOGGER.FORTUNECOOKIEREST")));
  }

  private static GenericContainer<?> createPaymentApplication() {
    return new GenericContainer<>("payment:latest")
        .withExposedPorts(8081)
        .withNetwork(network)
        .withNetworkAliases("payment")
        .withLogConsumer(
            outputFrame ->
                logContainerMsg(
                    outputFrame.getUtf8String(),
                    "APP:",
                    LoggerFactory.getLogger("CONTAINER_LOGGER.PAYMENT")));
  }

  private static PostgreSQLContainer<?> createPostgresContainer() {
    return new PostgreSQLContainer<>("postgres:latest")
        .withExposedPorts(5432)
        .withNetwork(network)
        .withNetworkAliases("postgres")
        .withLogConsumer(outputFrame -> logContainerMsg(outputFrame.getUtf8String(), "POSTGRES:"));
  }

  private static void logContainerMsg(String message, String logPrefix) {
    logContainerMsg(message, logPrefix, LoggerFactory.getLogger("CONTAINER_LOGGER"));
  }

  private static void logContainerMsg(String message, String logPrefix, Logger logger) {
    if (message.contains(" ERROR [")) {
      logger.error("{} {}", logPrefix, message);
    } else if (message.contains(" WARN [")) {
      logger.warn("{} {}", logPrefix, message);
    } else if (message.contains(" DEBUG [")) {
      logger.debug("{} {}", logPrefix, message);
    } else if (message.contains(" TRACE [")) {
      logger.trace("{} {}", logPrefix, message);
    } else {
      logger.info("{} {}", logPrefix, message);
    }
  }
}
