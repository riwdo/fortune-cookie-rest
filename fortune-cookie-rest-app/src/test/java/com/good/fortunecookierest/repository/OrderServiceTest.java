package com.good.fortunecookierest.repository;

import static com.github.tomakehurst.wiremock.client.WireMock.*;

import com.github.tomakehurst.wiremock.WireMockServer;
import com.good.fortunecookierest.dto.OrderDto;
import com.good.fortunecookierest.model.Fortune;
import com.good.fortunecookierest.service.OrderService;
import com.good.fortunecookierest.service.PaymentService;
import java.util.Optional;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.web.client.RestClient;

public class OrderServiceTest {

  private final OrderRepository orderRepository = Mockito.mock(OrderRepository.class);
  private final FortuneRepository fortuneRepository = Mockito.mock(FortuneRepository.class);

  private final PaymentService paymentService =
      new PaymentService(wireMockServer.baseUrl(), RestClient.builder().build());
  ;
  private final OrderService orderService =
      new OrderService(orderRepository, fortuneRepository, paymentService);

  private static WireMockServer wireMockServer;

  @BeforeAll
  public static void setup() {
    wireMockServer = new WireMockServer();
    wireMockServer.start();
  }

  @AfterAll
  public static void tearDown() {
    wireMockServer.stop();
  }

  @BeforeEach
  public void setUp() {
    stubFor(post(urlEqualTo("/payment")).willReturn(aResponse().withStatus(200)));
  }

  @Test
  public void createOrderOk() {
    Optional<Fortune> fortune = Optional.of(Fortune.builder().build());
    Mockito.when(fortuneRepository.findById(1L)).thenReturn(fortune);

    orderService.createOrder(
        OrderDto.builder().fortuneId(1L).paymentType(OrderDto.PaymentType.KLARNA).build());
  }
}
