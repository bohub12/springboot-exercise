package com.example.demo.application.dto;

import com.example.demo.application.MemberRole;
import com.fasterxml.jackson.annotation.JsonProperty;

public record JsonPropertyResponseData(
        @JsonProperty("my_name")
        String myName,

        @JsonProperty("my_age")
        int myAge,

        @JsonProperty("my_role")
        MemberRole myRole
) {

}
