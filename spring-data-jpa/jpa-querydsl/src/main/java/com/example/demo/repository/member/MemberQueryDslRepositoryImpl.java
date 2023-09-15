package com.example.demo.repository.member;

import com.example.demo.domain.member.Member;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;

import java.util.List;
import static com.example.demo.domain.member.QMember.member;

@RequiredArgsConstructor
public class MemberQueryDslRepositoryImpl implements MemberQueryDslRepository {

    private final JPAQueryFactory jpaQueryFactory;

    @Override
    public List<Member> findManyByFirstnameIsKimOrPark() {
        return jpaQueryFactory
                .selectFrom(member)
                .where(member.firstName.eq("KIM").or(member.firstName.eq("PARK")))
                .fetch();
    }

    @Override
    public Long calculateMemberRows() {
        return jpaQueryFactory
                .select(member.count())
                .from(member)
                .fetchOne();
    }
}
