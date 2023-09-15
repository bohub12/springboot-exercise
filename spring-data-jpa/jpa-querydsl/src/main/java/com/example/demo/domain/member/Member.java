package com.example.demo.domain.member;

import com.example.demo.domain.team.Team;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
public class Member {

    @Id @GeneratedValue
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    private Team team;

    private String firstName;
    private String lastName;

    @Embedded
    private Address address;
    private MemberRole role;

    public Member(Team team, String firstName, String lastName, Address address, MemberRole role) {
        this.team = team;
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.role = role;
    }
}
