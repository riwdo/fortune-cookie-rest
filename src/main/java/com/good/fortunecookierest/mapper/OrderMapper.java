package com.good.fortunecookierest.mapper;

import com.good.fortunecookierest.dto.AddressDto;
import com.good.fortunecookierest.dto.OrderResponseDto;
import com.good.fortunecookierest.model.Order;
import java.util.List;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface OrderMapper {

  List<OrderResponseDto> mapOrderResponse(List<Order> order);

  @Mapping(source = "fortune.id", target = "fortuneId")
  OrderResponseDto mapOrderResponse(Order order);

  AddressDto mapAddress(Order.Address address);
}
