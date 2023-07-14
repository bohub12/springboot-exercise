package com.example.demo.common;

import com.example.demo.domain.Food;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class EnumFinderTest {

    @Test
    public void enumFinderTest() {
        Food banana = EnumFinder.findBy(Food.class, Food::getType, "바나나");

        assertThat(banana.getType()).isEqualTo("바나나");
        assertThat(banana.getAverageCalories()).isEqualTo(110);
    }

    @Test
    public void enumFinderFailTest() {
        Food banana = EnumFinder.findBy(Food.class, Food::getType, "사자");

        assertThat(banana).isNull();
    }
}
