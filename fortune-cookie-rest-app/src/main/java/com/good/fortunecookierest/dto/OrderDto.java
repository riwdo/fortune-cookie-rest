package com.good.fortunecookierest.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class OrderDto {

  private Long fortuneId;
  private PaymentType paymentType;

  public enum PaymentType {
    @JsonProperty("swish")
    SWISH,
    @JsonProperty("klarna")
    KLARNA,
    @JsonProperty("card")
    CARD
  }
}
