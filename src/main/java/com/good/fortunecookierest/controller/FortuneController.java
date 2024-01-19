package com.good.fortunecookierest.controller;

import com.good.fortunecookierest.model.Fortune;
import com.good.fortunecookierest.repository.FortuneRepository;
import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FortuneController {

  private final FortuneRepository fortuneRepository;

  public FortuneController(FortuneRepository fortuneRepository) {
    this.fortuneRepository = fortuneRepository;
  }

  @GetMapping("/fortunes")
  public ResponseEntity<List<Fortune>> getFortunes() {
    return ResponseEntity.ok(fortuneRepository.findAll());
  }

  @GetMapping("/fortunes/distinct")
  public ResponseEntity<List<Fortune>> getFortunesDistinct() {
    return ResponseEntity.ok(fortuneRepository.findAll());
  }
}
