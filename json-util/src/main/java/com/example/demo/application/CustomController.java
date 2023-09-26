package com.example.demo.application;

import com.example.demo.application.dto.JsonIgnoreRequestData;
import com.example.demo.application.dto.JsonNamingResponseData;
import com.example.demo.application.dto.JsonPropertyResponseData;
import com.example.demo.application.dto.MemberJsonIgnorePropertyGettersRequest;
import com.example.demo.application.dto.MemberJsonIgnorePropertyResponse;
import com.example.demo.application.dto.MemberJsonIgnorePropertySettersResponse;
import com.example.demo.application.dto.MemberJsonIgnoreResponse;
import com.example.demo.application.dto.MemberJsonIgnoreTypeResponse;
import com.example.demo.infrastructure.MemberRepository;
import com.example.demo.infrastructure.TeamRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
public class CustomController {

    private final MemberRepository memberRepository;
    private final TeamRepository teamRepository;

    @GetMapping("/property")
    public JsonPropertyResponseData getDataUsingJsonProperty() {
        return new JsonPropertyResponseData("member1", 10, MemberRole.NORMAL);
    }

    @GetMapping("/naming")
    public JsonNamingResponseData getDataUsingJsonNaming() {
        return new JsonNamingResponseData("member1", 10, MemberRole.NORMAL);
    }

    @GetMapping("/ignore")
    public List<MemberJsonIgnoreResponse> getDataUsingJsonIgnore() {
        return memberRepository.findAll().stream().map(MemberJsonIgnoreResponse::new).toList();
    }

    @GetMapping("/ignore-property")
    public List<MemberJsonIgnorePropertyResponse> getDataUsingJsonIgnoreProperty() {
        return memberRepository.findAll().stream().map(MemberJsonIgnorePropertyResponse::new).toList();
    }

    @PostMapping("/ignore-property-unknown")
    public JsonIgnoreRequestData getDataUsingJsonIgnorePropertyByIgnoreUnknown(@RequestBody JsonIgnoreRequestData data) {
        log.info("request body : {}", data);
        return data;
    }

    @PostMapping("/ignore-property-getters")
    public MemberJsonIgnorePropertyGettersRequest getDataUsingJsonIgnorePropertyByGetters(@RequestBody MemberJsonIgnorePropertyGettersRequest data) {
        log.info("request body : {}", data);
        return data;
    }

    @GetMapping("/ignore-property-setters")
    public List<MemberJsonIgnorePropertySettersResponse> getDataUsingJsonIgnorePropertyBySetters() {
        return memberRepository.findAll().stream().map(MemberJsonIgnorePropertySettersResponse::new).toList();
    }

    @GetMapping("/ignore-type")
    public List<MemberJsonIgnoreTypeResponse> getDataUsingJsonIgnoreType() {
        return memberRepository.findAll().stream().map(MemberJsonIgnoreTypeResponse::new).toList();
    }



}
