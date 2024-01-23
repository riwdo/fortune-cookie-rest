package com.good.fortunecookierest.exception;

import org.springframework.http.HttpStatusCode;
import org.springframework.web.client.HttpStatusCodeException;

public class BadRequestException extends HttpStatusCodeException {
  public BadRequestException(String message) {
    super(HttpStatusCode.valueOf(400), message);
  }
}
