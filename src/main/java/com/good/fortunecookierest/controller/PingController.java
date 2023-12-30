package com.good.fortunecookierest.controller;

import com.good.fortunecookierest.model.Fortune;
import com.good.fortunecookierest.repository.FortuneRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class PingController {

    private final FortuneRepository fortuneRepository;

    public PingController(FortuneRepository fortuneRepository) {
        this.fortuneRepository = fortuneRepository;
    }


    @GetMapping("/ping")
    public ResponseEntity<String> ping() {
        return ResponseEntity.ok("pong");
    }


    @GetMapping("/fortunes")
    public ResponseEntity<List<Fortune>> getFortunes() {
        return ResponseEntity.ok(fortuneRepository.findAll());
    }
}
