package com.good.fortunecookierest.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class AddressDto {

  @NotNull private String street;
  @NotNull private String postalCode;
  @NotNull private String city;
}
