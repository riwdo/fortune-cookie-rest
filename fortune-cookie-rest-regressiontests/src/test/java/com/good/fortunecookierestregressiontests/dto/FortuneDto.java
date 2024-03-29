package com.good.fortunecookierestregressiontests.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class FortuneDto {

  private Long id;
  private String description;
  private String author;
}
