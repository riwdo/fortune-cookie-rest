package com.good.fortunecookierest.controller;

import com.good.fortunecookierest.dto.AddressDto;
import com.good.fortunecookierest.dto.OrderDto;
import com.good.fortunecookierest.dto.OrderResponseDto;
import com.good.fortunecookierest.mapper.OrderMapper;
import com.good.fortunecookierest.service.OrderService;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/orders")
@Slf4j
public class OrderController {

  private final OrderService orderService;
  private final OrderMapper orderMapper;

  public OrderController(OrderService orderService, OrderMapper orderMapper) {
    this.orderService = orderService;
    this.orderMapper = orderMapper;
  }

  @GetMapping
  public ResponseEntity<List<OrderResponseDto>> getOrders() {
    log.info("GET /orders");
    return ResponseEntity.ok(
        orderService.getOrders().stream().map(orderMapper::mapOrderResponse).toList());
  }

  @PostMapping
  public ResponseEntity<OrderResponseDto> postOrder(@RequestBody OrderDto orderDto) {
    log.info("POST /orders");
    return orderService
        .createOrder(orderDto)
        .map(orderMapper::mapOrderResponse)
        .map(ResponseEntity::ok)
        .orElse(ResponseEntity.notFound().build());
  }

  @PostMapping("/{id}/bake")
  public ResponseEntity<OrderResponseDto> startBaking(@PathVariable Long id) {
    log.info("POST /orders/{}/bake", id);
    var orderResponse = orderService.startBaking(id).map(orderMapper::mapOrderResponse);
    return orderResponse.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
  }

  @PostMapping("/{id}/deliver")
  public ResponseEntity<OrderResponseDto> deliver(
      @PathVariable Long id, @RequestBody AddressDto addressDto) {
    log.info("POST /orders/{}/deliver", id);
    var orderResponse = orderService.deliver(id, addressDto).map(orderMapper::mapOrderResponse);
    return orderResponse.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
  }
}
