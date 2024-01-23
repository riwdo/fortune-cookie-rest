package com.good.fortunecookierest.controller;

import com.good.fortunecookierest.dto.FortuneDto;
import com.good.fortunecookierest.mapper.OrderMapper;
import com.good.fortunecookierest.repository.FortuneRepository;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
public class FortuneController {

  private final OrderMapper orderMapper;

  private final FortuneRepository fortuneRepository;

  public FortuneController(OrderMapper orderMapper, FortuneRepository fortuneRepository) {
    this.orderMapper = orderMapper;
    this.fortuneRepository = fortuneRepository;
  }

  @GetMapping("/fortunes")
  public ResponseEntity<List<FortuneDto>> getFortunes() {
    log.info("GET /fortunes");
    return ResponseEntity.ok(
        fortuneRepository.findAll().stream().map(orderMapper::mapFortune).toList());
  }
}
