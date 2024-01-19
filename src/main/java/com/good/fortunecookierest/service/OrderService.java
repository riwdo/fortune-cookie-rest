package com.good.fortunecookierest.service;

import com.good.fortunecookierest.dto.AddressDto;
import com.good.fortunecookierest.dto.OrderDto;
import com.good.fortunecookierest.exception.BadRequestException;
import com.good.fortunecookierest.model.Order;
import com.good.fortunecookierest.repository.FortuneRepository;
import com.good.fortunecookierest.repository.OrderRepository;
import jakarta.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import org.hibernate.Hibernate;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class OrderService {

  private final OrderRepository orderRepository;
  private final FortuneRepository fortuneRepository;

  public OrderService(OrderRepository orderRepository, FortuneRepository fortuneRepository) {
    this.orderRepository = orderRepository;
    this.fortuneRepository = fortuneRepository;
  }

  public List<Order> getOrders() {
    return orderRepository.findAll().stream()
        .map(
            order -> {
              Hibernate.initialize(order.getFortune());
              return order;
            })
        .toList();
  }

  public Optional<Order> createOrder(OrderDto orderDto) {
    var fortune = fortuneRepository.findById(orderDto.getFortuneId());

    if (fortune.isEmpty()) {
      return Optional.empty();
    }

    Order order = Order.builder().status(Order.Status.RECEIVED).fortune(fortune.get()).build();
    orderRepository.save(order);
    return Optional.of(order);
  }

  public Optional<Order> startBaking(Long orderId) {
    return orderRepository
        .findById(orderId)
        .map(
            order -> {
              if (order.getStatus() != Order.Status.RECEIVED) {
                throw new BadRequestException("Order status is not in received");
              }
              order.setStatus(Order.Status.BAKING);
              return order;
            });
  }

  public Optional<Order> deliver(Long orderId, AddressDto addressDto) {
    return orderRepository
        .findById(orderId)
        .map(
            order -> {
              if (order.getStatus() != Order.Status.BAKING) {
                throw new BadRequestException("Order status is not in baking");
              }

              order.setStatus(Order.Status.DELIVERED);
              order.setAddress(mapToAddress(addressDto));
              return order;
            });
  }

  private Order.Address mapToAddress(AddressDto addressDto) {
    return Order.Address.builder()
        .street(addressDto.getStreet())
        .postalCode(addressDto.getPostalCode())
        .city(addressDto.getCity())
        .build();
  }
}
