package com.example.demo.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum Food {
    APPLE("사과", 90),
    BANANA("바나나", 110),
    ORANGE("오렌지", 70);

    private final String type;
    private final int averageCalories;

}
