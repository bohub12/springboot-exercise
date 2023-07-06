package com.example.demo.interceptor;

import com.example.demo.price.PricingPlanBasedAPIService;
import com.example.demo.price.PricingPlanBasedHostService;
import com.example.demo.request.RequestInfo;
import io.github.bucket4j.Bucket;
import io.github.bucket4j.ConsumptionProbe;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

@Component
@RequiredArgsConstructor
public class RateLimitBasedAPIInterceptor implements HandlerInterceptor {
    private static final String HEADER_API_KEY = "x-api-key";
    private static final String HEADER_LIMIT_REMAINING = "X-RateLimit-Remaining";
    private static final String HEADER_RETRY_AFTER = "X-RateLimit-Retry-After-Seconds";

    private final PricingPlanBasedAPIService pricingPlanBasedAPIService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String apiKey = request.getHeader(HEADER_API_KEY);
        String requestMethod = request.getMethod().toUpperCase();
        String requestURI = request.getRequestURI();

        RequestInfo requestInfo = new RequestInfo(apiKey, requestMethod, requestURI);

        if (apiKey == null || apiKey.isEmpty()) {
            response.sendError(HttpStatus.BAD_REQUEST.value(), "Missing Header: " + HEADER_API_KEY);
            return false;
        }

        Bucket tokenBucket = pricingPlanBasedAPIService.resolveBucket(requestInfo);
        ConsumptionProbe probe = tokenBucket.tryConsumeAndReturnRemaining(1);

        if (probe.isConsumed()) {
            response.addHeader(HEADER_LIMIT_REMAINING, String.valueOf(probe.getRemainingTokens()));

            return true;
        } else {
            long waitForRefill = probe.getNanosToWaitForRefill() / 1_000_000_000;
            response.addHeader(HEADER_RETRY_AFTER, String.valueOf(waitForRefill));
            response.sendError(HttpStatus.TOO_MANY_REQUESTS.value(), "too many request");

            return false;
        }
    }
}
