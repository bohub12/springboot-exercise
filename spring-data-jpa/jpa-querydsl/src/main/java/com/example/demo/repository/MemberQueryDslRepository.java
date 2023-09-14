package com.example.demo.repository;

import com.example.demo.domain.Member;

import java.util.List;

public interface MemberQueryDslRepository {
    List<Member> findManyByFirstnameIsKimOrPark();
    int calculateMemberRows();
}
