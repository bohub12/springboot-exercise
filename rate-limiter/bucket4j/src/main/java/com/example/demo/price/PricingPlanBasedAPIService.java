package com.example.demo.price;

import com.example.demo.request.RequestInfo;
import io.github.bucket4j.Bucket;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class PricingPlanBasedAPIService {

    private final Map<RequestInfo, Bucket> cache = new ConcurrentHashMap<>();

    public Bucket resolveBucket(RequestInfo requestInfo) {
        return cache.computeIfAbsent(requestInfo, this::newBucket);
    }

    private Bucket newBucket(RequestInfo requestInfo) {
        PricingPlan pricingPlan = PricingPlan.resolvePlanFromRequestInfo(requestInfo);
        return Bucket.builder()
                .addLimit(pricingPlan.getLimit())
                .build();
    }
}
