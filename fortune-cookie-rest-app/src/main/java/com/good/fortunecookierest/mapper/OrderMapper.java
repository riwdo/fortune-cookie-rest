package com.good.fortunecookierest.mapper;

import com.good.fortunecookierest.dto.AddressDto;
import com.good.fortunecookierest.dto.FortuneDto;
import com.good.fortunecookierest.dto.OrderResponseDto;
import com.good.fortunecookierest.model.Fortune;
import com.good.fortunecookierest.model.Order;
import java.util.List;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface OrderMapper {

  List<OrderResponseDto> mapOrderResponse(List<Order> order);

  OrderResponseDto mapOrderResponse(Order order);

  FortuneDto mapFortune(Fortune fortune);

  AddressDto mapAddress(Order.Address address);
}
