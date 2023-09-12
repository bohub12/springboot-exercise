package com.example.demo.controller.dto;

import com.example.demo.domain.MemberRole;

public record UpdateMemberRequestDto(long id, String name, MemberRole role, int age) {
}
