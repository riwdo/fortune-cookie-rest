package com.good.fortunecookierest.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class FortuneDto {

  private Long id;
  private String description;
  private String author;
}
