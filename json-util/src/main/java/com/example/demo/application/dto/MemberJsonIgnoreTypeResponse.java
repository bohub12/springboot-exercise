package com.example.demo.application.dto;

import com.example.demo.domain.Member;
import com.example.demo.domain.Team;
import com.fasterxml.jackson.annotation.JsonIgnoreType;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
public class MemberJsonIgnoreTypeResponse {
    private Long id;
    private CustomType customType;
    public MemberJsonIgnoreTypeResponse(Member member) {
        this.id = member.getId();
        this.customType = new CustomType(member.getName(), member.getAge());
    }

    @AllArgsConstructor
    @JsonIgnoreType(true)
    static class CustomType {
        private String name;
        private int age;
    }
}
