package com.good.fortunecookierest.service;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.good.fortunecookierest.exception.BadRequestException;
import com.good.fortunecookierest.model.Order;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

@Service
@Slf4j
@Transactional
public class PaymentService {

  private final String baseUrl;
  private final RestClient restClient;

  public PaymentService(@Value("${api.payment.base-url}") String baseUrl, RestClient restClient) {
    this.baseUrl = baseUrl;
    this.restClient = restClient;
  }

  public void sendPayment(Order order) {
    log.info("POST {}/payment", baseUrl);

    var r =
        restClient
            .post()
            .uri(baseUrl + "/payment")
            .accept(MediaType.APPLICATION_JSON)
            .body(
                Payment.builder()
                    .orderId(order.getId())
                    .type(Payment.Type.valueOf(order.getPaymentType().name()))
                    .build())
            .retrieve()
            .onStatus(
                HttpStatusCode::is4xxClientError,
                (request, response) -> {
                  throw new BadRequestException(response.getStatusText());
                })
            .toBodilessEntity();

    log.info(
        "Payment method set to {} for order with id {}", order.getPaymentType(), order.getId());
  }

  @Data
  @Builder
  @AllArgsConstructor
  @NoArgsConstructor
  public static class Payment {

    private Long orderId;
    private Type type;

    public enum Type {
      @JsonProperty("swish")
      SWISH,
      @JsonProperty("klarna")
      KLARNA,
      @JsonProperty("card")
      CARD
    }
  }
}
