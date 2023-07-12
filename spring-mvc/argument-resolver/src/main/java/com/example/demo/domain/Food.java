package com.example.demo.domain;

import lombok.Getter;

@Getter
public enum Food {
    APPLE("사과"),
    ORANGE("오렌지"),
    BANANA("바나나");

    private final String type;

    Food(String type) {
        this.type = type;
    }
}
