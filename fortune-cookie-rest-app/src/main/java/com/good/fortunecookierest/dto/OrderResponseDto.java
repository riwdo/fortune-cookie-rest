package com.good.fortunecookierest.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class OrderResponseDto {

  private Long id;
  private FortuneDto fortune;
  private Status status;
  private AddressDto address;

  public enum Status {
    @JsonProperty("received")
    RECEIVED,
    @JsonProperty("baking")
    BAKING,
    @JsonProperty("delivered")
    DELIVERED
  }
}
