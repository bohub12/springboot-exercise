package com.example.demo.controller;

import com.example.demo.controller.dto.CreateMemberDto;
import com.example.demo.domain.member.Member;
import com.example.demo.repository.member.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/members")
@RequiredArgsConstructor
public class MemberController {

    private final MemberRepository repository;

    @GetMapping("count")
    public Long memberCount() {
        return repository.calculateEntireRows();
    }

    @GetMapping("firstname-is-kim-or-park")
    public List<Member> findMemberKimOrPark() {
        return repository.findMembersByFirstnameIsKimOrPark();
    }

    @PostMapping("")
    public Member save(@RequestBody CreateMemberDto body) {
        return repository.save(body.toEntity());
    }
}
