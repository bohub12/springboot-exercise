package com.example.demo.service;

import com.example.demo.domain.Member;
import com.example.demo.repostiory.MemberRepository;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
public class InnerService {

    private final MemberRepository memberRepository;
    private final EntityManager em;

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void saveMemberInRequiresNewMode() {
        log.info("(inner) transaction session id = {}", em.getDelegate());

        Member member = new Member("member2", 20);
        memberRepository.save(member);
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public void saveMemberInRequiredMode() {
        log.info("(inner) transaction session id = {}", em.getDelegate());

        Member member = new Member("member2", 20);
        memberRepository.save(member);
    }
}
