package com.example.demo.dto;

import com.example.demo.common.EnumFinder;
import com.example.demo.domain.Address;
import com.example.demo.domain.Food;
import jakarta.validation.Valid;
import jakarta.validation.constraints.AssertTrue;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@ToString
@Getter
@Builder
@AllArgsConstructor
public class MemberSaveDto {

    @NotNull
    private String name;

    @Email
    @NotNull
    private String email;

    @Valid
    private Address address;

    @NotNull
    private String favoriteFood;

    @AssertTrue
    public boolean isValidFood() {
        return EnumFinder.findBy(Food.class, Food::getType, favoriteFood) != null;
    }
}
