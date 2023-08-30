package com.example.demo.member;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository repository;

    public Member findOne(Long id) {
        return repository.findById(id).orElseThrow(NullPointerException::new);
    }

    public List<Member> findAll() {
        return repository.findAll();
    }
}
