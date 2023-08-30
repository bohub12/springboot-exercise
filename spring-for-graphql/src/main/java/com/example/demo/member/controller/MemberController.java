package com.example.demo.member.controller;

import com.example.demo.member.Member;
import com.example.demo.member.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/restful-api")
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;

    @GetMapping("/{id}")
    public Member findOne(@PathVariable Long id) {
        return memberService.findOne(id);
    }

    @GetMapping
    public List<Member> findAll() {
        return memberService.findAll();
    }
}
