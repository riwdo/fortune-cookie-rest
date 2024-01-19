package com.good.fortunecookierest.controller;

import com.good.fortunecookierest.dto.AddressDto;
import com.good.fortunecookierest.dto.OrderDto;
import com.good.fortunecookierest.dto.OrderResponseDto;
import com.good.fortunecookierest.mapper.OrderMapper;
import com.good.fortunecookierest.model.Order;
import com.good.fortunecookierest.service.OrderService;
import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/orders")
public class OrderController {

  private final OrderService orderService;
  private final OrderMapper orderMapper;

  public OrderController(OrderService orderService, OrderMapper orderMapper) {
    this.orderService = orderService;
    this.orderMapper = orderMapper;
  }

  @GetMapping
  public ResponseEntity<List<OrderResponseDto>> getOrders() {
    return ResponseEntity.ok(
        orderService.getOrders().stream().map(orderMapper::mapOrderResponse).toList());
  }

  @PostMapping
  public ResponseEntity<Order> postOrder(@RequestBody OrderDto orderDto) {
    return orderService
        .createOrder(orderDto)
        .map(ResponseEntity::ok)
        .orElse(ResponseEntity.notFound().build());
  }

  @PostMapping("/{id}/bake")
  public ResponseEntity<OrderResponseDto> startBaking(@PathVariable Long id) {
    var orderResponse = orderService.startBaking(id).map(orderMapper::mapOrderResponse);
    return orderResponse.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
  }

  @PostMapping("/{id}/deliver")
  public ResponseEntity<OrderResponseDto> deliver(
      @PathVariable Long id, @RequestBody AddressDto addressDto) {
    var orderResponse = orderService.deliver(id, addressDto).map(orderMapper::mapOrderResponse);
    return orderResponse.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
  }
}
