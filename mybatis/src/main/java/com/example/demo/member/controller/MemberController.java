package com.example.demo.member.controller;

import com.example.demo.member.domain.Member;
import com.example.demo.member.dto.MemberSaveDto;
import com.example.demo.member.dto.MemberPatchDto;
import com.example.demo.member.mapper.MemberMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/members")
@RequiredArgsConstructor
public class MemberController {

    private final MemberMapper memberMapper;

    @GetMapping("")
    public ResponseEntity<?> getMember(@RequestParam("id") String id) {
        Member member = memberMapper.getMember(id);
        return ResponseEntity.ok().body(member);
    }

    @PostMapping("")
    public ResponseEntity<?> saveMember(@RequestBody MemberSaveDto body) {
        memberMapper.saveMember(Member.from(body));
        return ResponseEntity.ok().body("ok");
    }

    @PatchMapping("")
    public ResponseEntity<?> updateMember(@RequestBody MemberPatchDto body) {
        memberMapper.updateMember(Member.from(body));
        return ResponseEntity.ok().body("ok");
    }

    @DeleteMapping("")
    public ResponseEntity<?> deleteMember(@RequestParam("id") String id) {
        memberMapper.deleteMember(id);
        return ResponseEntity.ok().body("delete success");
    }
}
