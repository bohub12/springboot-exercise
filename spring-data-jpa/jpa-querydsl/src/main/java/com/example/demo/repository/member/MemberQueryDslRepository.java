package com.example.demo.repository.member;

import com.example.demo.domain.member.Member;

import java.util.List;

public interface MemberQueryDslRepository {
    List<Member> findManyByFirstnameIsKimOrPark();
    Long calculateMemberRows();
}
