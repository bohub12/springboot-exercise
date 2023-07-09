package com.example.demo.price;

import com.example.demo.request.RequestInfo;
import io.github.bucket4j.Bandwidth;
import io.github.bucket4j.Refill;

import java.time.Duration;

public enum PricingPlan {

    FREE(20),

    BASIC(40),

    PROFESSIONAL(100);

    private final int bucketCapacity;

    private PricingPlan(int bucketCapacity) {
        this.bucketCapacity = bucketCapacity;
    }

    Bandwidth getLimit() {
        return Bandwidth.classic(bucketCapacity, Refill.intervally(bucketCapacity, Duration.ofMinutes(1)));
    }

    public int bucketCapacity() {
        return bucketCapacity;
    }

    static PricingPlan resolvePlanFromApiKey(String apiKey) {
        if (apiKey == null || apiKey.isEmpty()) {
            return FREE;

        } else if (apiKey.startsWith("PX001-")) {
            return PROFESSIONAL;

        } else if (apiKey.startsWith("BX001-")) {
            return BASIC;
        }
        return FREE;
    }

    static PricingPlan resolvePlanFromRequestInfo(RequestInfo requestInfo) {
        return resolvePlanFromApiKey(requestInfo.getApiKey());
    }
}
