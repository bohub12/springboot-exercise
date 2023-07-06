package com.example.demo.request;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;

@Getter
@AllArgsConstructor
@EqualsAndHashCode
public class RequestInfo {
    private final String apiKey;
    private final String requestMethod;
    private final String requestURI;
}
