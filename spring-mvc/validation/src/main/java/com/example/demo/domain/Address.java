package com.example.demo.domain;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;
import org.hibernate.validator.constraints.Length;
import org.springframework.validation.annotation.Validated;

//@Getter
@ToString
@AllArgsConstructor
public class Address {

    @NotNull
    @Size(min = 5, max = 5)
    private String zipCode;

    @NotNull
    private String detailAddress;
}
