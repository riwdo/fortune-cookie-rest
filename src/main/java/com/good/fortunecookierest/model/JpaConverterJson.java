package com.good.fortunecookierest.model;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter
public class JpaConverterJson implements AttributeConverter<Order.Address, String> {

  private final ObjectMapper objectMapper = new ObjectMapper();

  @Override
  public String convertToDatabaseColumn(Order.Address object) {
    if (object == null) {
      return null;
    }
    try {
      return objectMapper.writeValueAsString(object);
    } catch (JsonProcessingException e) {
      throw new RuntimeException("Error converting Object to JSON", e);
    }
  }

  @Override
  public Order.Address convertToEntityAttribute(String dbData) {
    if (dbData == null) {
      return null;
    }
    try {
      return objectMapper.readValue(dbData, Order.Address.class);
    } catch (JsonProcessingException e) {
      throw new RuntimeException("Error converting JSON to Object", e);
    }
  }
}
