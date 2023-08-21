package com.example.demo.member.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Getter
@NoArgsConstructor
public class MemberSaveDto {
    private final String id = String.valueOf(UUID.randomUUID());
    private String name;

    public MemberSaveDto(String name) {
        this.name = name;
    }
}
