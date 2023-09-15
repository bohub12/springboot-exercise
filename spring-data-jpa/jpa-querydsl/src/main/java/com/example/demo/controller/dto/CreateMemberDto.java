package com.example.demo.controller.dto;

import com.example.demo.domain.member.Address;
import com.example.demo.domain.member.Member;
import com.example.demo.domain.member.MemberRole;
import com.example.demo.domain.team.Team;

public record CreateMemberDto(long teamId, String firstName, String lastName, Address address, MemberRole role) {

    public Member toEntity() {
        return new Member(new Team(teamId), firstName, lastName, address, role);
    }
}
