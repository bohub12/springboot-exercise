package com.example.demo.application.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;


/**
 * @JsonIgnoreProperties(ignoreUnknown = true) 없어도 모르는 json 필드 있어도 문제 없이 정상 동작한다.
 * 이유는 jackson.deserialization.fail-on-unknown-properties 값이 기본적으로 false인데,
 * true로 설정되어 있을 때에만 모르는 필드가 있을 때, 에러를 발생시킨다고 한다.
 * 즉 기본설정으로 따라갈거면 필요없는 어노테이션이다.
 * 만약 클라이언트로부터 불필요한 요청 값 오는 것 자체를 방지하기 위해, 기본설정을 바꾸면 유효한 어노테이션일 것이다.
 */
@Data
//@JsonIgnoreProperties(ignoreUnknown = true)
public class JsonIgnoreRequestData {
    private String name;
    private int age;
}
