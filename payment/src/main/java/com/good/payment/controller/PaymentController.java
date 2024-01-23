package com.good.payment.controller;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
public class PaymentController {

    @PostMapping(path = "/payment")
    public ResponseEntity<?> payment(@RequestBody Payment payment) {
        log.info("POST /payment");
        log.info("Use paymentType {} for order {}", payment.getType(), payment.getOrderId());
        return ResponseEntity.ok().build();
    }

    @Data
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Payment {

        private String orderId;
        private Type type;

        public enum Type {
            @JsonProperty("swish")
            SWISH,
            @JsonProperty("card")
            CARD
        }

    }



}
