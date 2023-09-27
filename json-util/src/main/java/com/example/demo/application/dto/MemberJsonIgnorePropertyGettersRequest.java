package com.example.demo.application.dto;

import com.example.demo.domain.Member;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * id, name 직렬화 안됨
 * request 로 받아도 id, name은 null, 0 으로 설정됨
 */
@Getter
@AllArgsConstructor
@JsonIgnoreProperties(value = {"id", "name"}, allowGetters = true)
public class MemberJsonIgnorePropertyGettersRequest {

    private Long id;

    private String name;

    private int age;

    public MemberJsonIgnorePropertyGettersRequest(Member member) {
        this.id = member.getId();
        this.name = member.getName();
        this.age = member.getAge();
    }
}
