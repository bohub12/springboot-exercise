package com.example.demo.domain.team;

import com.example.demo.domain.member.Member;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
public class Team {
    @Id @GeneratedValue
    private long id;

    private String name;

    public Team (String name) {
        this.name = name;
    }

    public Team (long id) {
        this.id = id;
    }
}
