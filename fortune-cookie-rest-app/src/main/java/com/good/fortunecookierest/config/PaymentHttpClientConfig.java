package com.good.fortunecookierest.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.web.client.RestClient;

@Configuration
public class PaymentHttpClientConfig {

  @Bean
  @Primary
  public RestClient restClient() {
    return RestClient.create();
  }
}
