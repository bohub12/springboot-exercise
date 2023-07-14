package com.example.demo.common;

import java.util.function.Function;

public class EnumFinder {
    public static <E extends Enum<E>, V> E findBy(Class<E> enumClass, Function<E, V> getSelector, V selector) {
        for (E e: enumClass.getEnumConstants()) {
            if (getSelector.apply(e).equals(selector)) {
                return e;
            }
        }
        return null;
    }
}
