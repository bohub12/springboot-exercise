package com.example.demo.controller.dto;

import com.example.demo.domain.Member;
import com.example.demo.domain.MemberRole;

public record CreateMemberRequestDto(String name, MemberRole role, int age) {

    public Member toEntity() {
        return new Member(name, role, age);
    }
}
