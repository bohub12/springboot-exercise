package com.example.demo.service;

import com.example.demo.domain.Member;
import com.example.demo.repostiory.MemberRepository;
import jakarta.persistence.EntityManager;
import java.io.IOException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.TransactionOperations;
import org.springframework.transaction.support.TransactionTemplate;

@Service
@Slf4j
@RequiredArgsConstructor
public class CustomService {

    private final MemberRepository memberRepository;
    private final InnerService innerService;
    private final TransactionOperations transactionOperations;
    private final TransactionTemplate transactionTemplate;
    private final EntityManager em;


    @Transactional
    public void saveMemberThrowCheckedException() throws IOException {
        memberRepository.save(new Member("member1", 10));
        throwCheckedException();
    }
    
    @Transactional
    public void saveMemberThrowUnCheckedException() {
        memberRepository.save(new Member("member1", 10));
        throwUncheckedException();
    }

    @Transactional(noRollbackFor = {RuntimeException.class})
    public void saveMemberIgnoreUnCheckedException() {
        memberRepository.save(new Member("member1", 10));
        throwUncheckedException();
    }

    @Transactional
    public void saveMemberInRequiresNewMode() {
        log.info("(outer) transaction session id = {}", em.getDelegate());

        memberRepository.save(new Member("member1", 10));
        innerService.saveMemberInRequiresNewMode();
        memberRepository.save(new Member("member3", 30));
        throwUncheckedException();
    }

    @Transactional
    public void saveMemberInRequiredMode() {
        log.info("(outer) transaction session id = {}", em.getDelegate());

        memberRepository.save(new Member("member1", 10));
        innerService.saveMemberInRequiredMode();
        memberRepository.save(new Member("member3", 30));

        throwUncheckedException();
    }

    @Transactional
    public void saveMemberInSelfInvocation() {
        log.info("(outer) transaction session id = {}", em.getDelegate());

        memberRepository.save(new Member("member1", 10));
        selfInvocationMethod();
        memberRepository.save(new Member("member3", 30));

        throwUncheckedException();
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void selfInvocationMethod() {
        log.info("(self-invocation) transaction session id = {}", em.getDelegate());
        memberRepository.save(new Member("member2", 20));
    }

    private void throwUncheckedException() {
        throw new RuntimeException("잘못된 요청입니다.");
    }

    private void throwCheckedException() throws IOException {
        throw new IOException();
    }
}
