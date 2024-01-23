package com.good.fortunecookierestregressiontests.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
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
