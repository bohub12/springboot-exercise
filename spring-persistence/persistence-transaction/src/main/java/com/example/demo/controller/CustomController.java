package com.example.demo.controller;

import com.example.demo.service.CustomService;
import java.io.IOException;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/member")
@RequiredArgsConstructor
public class CustomController {

    private final CustomService customService;

    @PostMapping("/checked-exception")
    public String saveMemberThrowCheckedException() throws IOException {
        customService.saveMemberThrowCheckedException();
        return "ok";
    }

    @PostMapping("/unChecked-exception")
    public String saveMemberThrowUnCheckedException() {
        customService.saveMemberThrowUnCheckedException();
        return "ok";
    }

    @PostMapping("/ignore-unChecked-exception")
    public String saveMemberIgnoreUnCheckedException() {
        customService.saveMemberIgnoreUnCheckedException();
        return "ok";
    }

    @PostMapping("/requires-new")
    public String saveMemberInRequiresNewMode() {
        customService.saveMemberInRequiresNewMode();
        return "ok";
    }

    @PostMapping("/required")
    public String saveMemberInRequiredMode() {
        customService.saveMemberInRequiredMode();
        return "ok";
    }

    @PostMapping("/self-invocation")
    public String saveMemberSelfInvocation() {
        customService.saveMemberInSelfInvocation();
        return "ok";
    }


}
