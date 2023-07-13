package com.example.demo.controller;

import com.example.demo.domain.JwtAuthorization;
import com.example.demo.domain.Member;
import com.example.demo.jwt.JwtUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/member")
@RequiredArgsConstructor
public class MemberController {

    private final JwtUtils jwtUtils;

    @GetMapping("")
    public ResponseEntity<?> get(@JwtAuthorization Member member) {
        return ResponseEntity.ok().body(member.toString());
    }

    @PostMapping("")
    public ResponseEntity<?> save(@RequestParam("id") Long id, @RequestParam("name") String name) {
        return ResponseEntity.ok().body(jwtUtils.createToken(id, name));
    }
}
