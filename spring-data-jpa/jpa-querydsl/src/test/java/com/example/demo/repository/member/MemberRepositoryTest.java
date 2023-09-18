package com.example.demo.repository.member;

import com.example.demo.domain.member.Address;
import com.example.demo.domain.member.Member;
import com.example.demo.domain.member.MemberRole;
import com.example.demo.domain.team.Team;
import com.example.demo.repository.team.TeamRepository;
import jakarta.persistence.EntityManager;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class MemberRepositoryTest {

    @Autowired
    MemberRepository memberRepository;


    @BeforeEach
    void addDummyData(TeamRepository teamRepository, EntityManager em) {
        Team team1 = new Team("TEAM1");
        Team team2 = new Team("TEAM2");
        Team team3 = new Team("TEAM3");

        teamRepository.save(team1);
        teamRepository.save(team2);
        teamRepository.save(team3);


        Member member1 = new Member(team1, "KIM", "MOMO", new Address("00000", "101호"), MemberRole.NORMAL);
        Member member2 = new Member(team1, "KIM", "MOMO", new Address("11111", "202호"), MemberRole.ADMIN);
        Member member3 = new Member(team1, "PARK", "MOMO", new Address("22222", "303호"), MemberRole.NORMAL);
        Member member4 = new Member(team2, "RYU", "MOMO", new Address("33333", "404호"), MemberRole.ADMIN);
        Member member5 = new Member(team2, "LEE", "MOMO", new Address("44444", "505호"), MemberRole.NORMAL);

        memberRepository.save(member1);
        memberRepository.save(member2);
        memberRepository.save(member3);
        memberRepository.save(member4);
        memberRepository.save(member5);

        em.flush();
        em.clear();
    }
}