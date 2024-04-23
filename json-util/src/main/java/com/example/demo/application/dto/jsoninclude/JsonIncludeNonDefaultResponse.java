package com.example.demo.application.dto.jsoninclude;

import com.example.demo.application.MemberRole;
import com.example.demo.domain.Team;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import java.util.ArrayList;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
@JsonInclude(Include.NON_DEFAULT)
public class JsonIncludeNonDefaultResponse {
    private int defaultInt;
    private int normalInt;

    private Integer defaultWrapper;
    private Integer normalWrapper;

    private String nullString;
    private String emptyString;
    private String normalString;

    private Team nullReference;
    private Team normalReference;

    private List<String> emptyList;
    private List<String> normalList;

    public static JsonIncludeNonDefaultResponse create() {
        return new JsonIncludeNonDefaultResponse(0, 10,
                0, 10,
                null, "", "string",
                null, new Team("teamA"),
                new ArrayList<>(), List.of("str1")
        );
    }
}
