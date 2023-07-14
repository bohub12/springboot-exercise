package com.example.demo.controller;

import com.example.demo.dto.MemberSaveDto;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@Slf4j
@RequestMapping("/api/member")
public class MemberController {

    @PostMapping("")
    public ResponseEntity<?> save(@RequestBody @Valid MemberSaveDto body) {
        log.info(body.toString());

        // .. save logic ..

        return ResponseEntity.ok().build();
    }
}
