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
        Bucket bucket = pricingPlanService.resolveBucket(apiKey);
        ConsumptionProbe probe = bucket.tryConsumeAndReturnRemaining(1);

        if (probe.isConsumed()) {
            return ResponseEntity.ok()
                    .header("X-Rate-Limit-Remaining", Long.toString(probe.getRemainingTokens()))
                    .body("ok");
        }

        long waitForRefill = probe.getNanosToWaitForRefill() / 1_000_000_000;
        return ResponseEntity.status(HttpStatus.TOO_MANY_REQUESTS)
                .header("X-Rate-Limit-Retry-After-Seconds", String.valueOf(waitForRefill))
                .build();

    }
}
