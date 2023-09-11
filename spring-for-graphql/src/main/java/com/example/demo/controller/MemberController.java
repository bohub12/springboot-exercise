package com.example.demo.controller;

import com.example.demo.controller.dto.CreateMemberRequestDto;
import com.example.demo.domain.Member;
import com.example.demo.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class MemberController {

    private final MemberRepository repository;

    @GetMapping("/{id}")
    public Member findOne(@PathVariable Long id) {
        return repository.findById(id).orElseThrow();
    }

    @GetMapping
    public List<Member> findAll() {
        return repository.findAll();
    }

    @PostMapping
    public Member save(@RequestBody CreateMemberRequestDto body) {
        return repository.save(body.toEntity());
    }
}
