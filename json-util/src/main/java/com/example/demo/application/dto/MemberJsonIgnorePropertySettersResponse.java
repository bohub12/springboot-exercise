package com.example.demo.application.dto;

import com.example.demo.domain.Member;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * id, name 역직렬화 안됨.
 * response : [ {"age" : 1}, {..}, .. ]
 */
@Getter
@AllArgsConstructor
@JsonIgnoreProperties(value = {"id", "name"}, allowSetters = true)
public class MemberJsonIgnorePropertySettersResponse {

    private Long id;

    private String name;

    private int age;

    public MemberJsonIgnorePropertySettersResponse(Member member) {
        this.id = member.getId();
        this.name = member.getName();
        this.age = member.getAge();
    }
}
