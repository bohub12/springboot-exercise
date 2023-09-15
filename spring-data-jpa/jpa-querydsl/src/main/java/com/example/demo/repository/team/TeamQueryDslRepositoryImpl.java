package com.example.demo.repository.team;

import com.example.demo.domain.member.QMember;
import com.example.demo.domain.team.QTeam;
import com.example.demo.domain.team.Team;
import com.querydsl.core.Tuple;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;

import java.util.List;

import static com.example.demo.domain.member.QMember.member;
import static com.example.demo.domain.team.QTeam.team;


@RequiredArgsConstructor
public class TeamQueryDslRepositoryImpl implements TeamQueryDslRepository {

    private final JPAQueryFactory jpaQueryFactory;

    @Override
    public Long countTeams() {
        return jpaQueryFactory
                .select(team.count())
                .from(team)
                .fetchOne();
    }

    @Override
    public List<Team> findTeamsHavingMembersByArgument(long memberCount) {
        return jpaQueryFactory
                .selectFrom(team)
                .join(member).on(member.team.eq(team))
                .groupBy(team)
                .having(team.count().eq(memberCount))
                .fetch();

//        select team
//        from Team team
//        inner join Member member1 with member1.team = team
//        group by team
//        having count(team) = ?1

        // <잘못된 방식>
//        jpaQueryFactory
//                .selectFrom(team)
//                .join(member.team, team)
//                .groupBy(team)
//                .having(team.count().eq(memberCount))
//                .fetch();

//        select team
//        from Team team
//        inner join member1.team as team
//        group by team
//        having count(team) = ?1
    }
}
