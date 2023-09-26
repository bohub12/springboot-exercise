package com.example.demo.application.dto;

import com.example.demo.domain.Member;
import com.example.demo.domain.Team;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
@JsonIgnoreProperties(value = {"team", "name"})
public class MemberJsonIgnorePropertyResponse {

    private Long id;

    private Team team;

    private String name;

    private int age;

    public MemberJsonIgnorePropertyResponse(Member member) {
        this.id = member.getId();
        this.team = member.getTeam();
        this.name = member.getName();
        this.age = member.getAge();
    }
}