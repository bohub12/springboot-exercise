package com.example.demo.controller;

import com.example.demo.price.PricingPlanService;
import io.github.bucket4j.Bucket;
import io.github.bucket4j.ConsumptionProbe;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class TestController {

    private final PricingPlanService pricingPlanService;

    @GetMapping("/test")
    public ResponseEntity<?> test(@RequestHeader(value = "x-api-key") String apiKey) {
        return ResponseEntity.ok()
                .body("ok");

    }
}
