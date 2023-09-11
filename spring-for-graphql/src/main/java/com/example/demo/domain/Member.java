package com.example.demo.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Member {

    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;

    @Enumerated(value = EnumType.STRING)
    private MemberRole role;
    private int age;

    public Member(String name, MemberRole role, int age) {
        this.name = name;
        this.role = role;
        this.age = age;
    }
}
