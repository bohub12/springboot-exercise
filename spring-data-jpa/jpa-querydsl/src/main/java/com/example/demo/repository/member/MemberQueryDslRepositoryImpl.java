package com.example.demo.repository.member;

import com.example.demo.domain.member.Member;
import com.example.demo.domain.team.QTeam;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;

import java.util.List;
import static com.example.demo.domain.member.QMember.member;
import static com.example.demo.domain.team.QTeam.team;

@RequiredArgsConstructor
public class MemberQueryDslRepositoryImpl implements MemberQueryDslRepository {

    private final JPAQueryFactory jpaQueryFactory;

    @Override
    public List<Member> findMembersByFirstnameIsKimOrPark() {
        return jpaQueryFactory
                .selectFrom(member)
                .where(member.firstName.eq("KIM").or(member.firstName.eq("PARK")))
                .fetch();
    }

    @Override
    public Long calculateEntireRows() {
        return jpaQueryFactory
                .select(member.count())
                .from(member)
                .fetchOne();
    }

    @Override
    public List<Member> findMembersNotInAnyTeam() {
        return jpaQueryFactory
                .selectFrom(member)
                .leftJoin(member.team, team).fetchJoin()
                .where(member.team.id.isNull())
                .fetch();
    }
}
