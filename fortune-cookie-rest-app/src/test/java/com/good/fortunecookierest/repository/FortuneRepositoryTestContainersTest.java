package com.good.fortunecookierest.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.good.fortunecookierest.model.Fortune;
import java.time.LocalDateTime;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("testcontainer")
@Testcontainers
public class FortuneRepositoryTestContainersTest {

  @Autowired private FortuneRepository fortuneRepository;

  @Container static PostgreSQLContainer<?> postgres = new PostgreSQLContainer<>("postgres:latest");

  @DynamicPropertySource
  static void postgresqlProperties(DynamicPropertyRegistry registry) {
    registry.add("spring.datasource.url", postgres::getJdbcUrl);
    registry.add("spring.datasource.password", postgres::getPassword);
    registry.add("spring.datasource.username", postgres::getUsername);
    registry.add("spring.flyway.user", postgres::getPassword);
    registry.add("spring.flyway.password", postgres::getUsername);
  }

  @Test
  public void saveOk() {
    Fortune fortune =
        Fortune.builder()
            .description(
                "In the garden of code, patience is the soil. Bugs, fleeting as clouds, come and go. Debug with a calm mind, and let go.")
            .createdAt(LocalDateTime.now())
            .author("Zen Master CodeSanity")
            .build();

    fortuneRepository.save(fortune);

    List<Fortune> fortuneList = fortuneRepository.findAll();
    assertEquals(1, fortuneList.size());
  }
}
