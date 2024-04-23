package com.example.demo.application.dto;

import com.example.demo.domain.Member;
import com.example.demo.domain.Team;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class MemberJsonIgnoreResponse {

    private Long id;

    @JsonIgnore
    private Team team;

    private String name;

    private int age;

    public MemberJsonIgnoreResponse(Member member) {
        this.id = member.getId();
        this.team = member.getTeam();
        this.name = member.getName();
        this.age = member.getAge();
    }
}
