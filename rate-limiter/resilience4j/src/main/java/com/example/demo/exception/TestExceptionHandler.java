package com.example.demo.exception;

import io.github.resilience4j.ratelimiter.RequestNotPermitted;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class TestExceptionHandler {

    @ExceptionHandler(RequestNotPermitted.class)
    public ResponseEntity<?> tooManyRequest() {
        return ResponseEntity.status(HttpStatus.TOO_MANY_REQUESTS).build();
    }
}
