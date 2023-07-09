package com.example.demo.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class TestController {

    @GetMapping("/test")
    public ResponseEntity<?> test(@RequestHeader(value = "x-api-key") String apiKey) {
        return ResponseEntity.ok()
                .body("ok");
    }

    @GetMapping("/test2")
    public ResponseEntity<?> test2(@RequestHeader(value = "x-api-key") String apiKey) {
        return ResponseEntity.ok()
                .body("ok2");
    }
}
