package com.example.demo.application.dto.jsonview;

import com.example.demo.application.dto.jsonview.View.DetailResponse;
import com.example.demo.application.dto.jsonview.View.SummaryResponse;
import com.fasterxml.jackson.annotation.JsonView;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class JsonViewResponse {

    private int normalInt;
    private String normalString;
    private List<String> normalList;

    @JsonView({SummaryResponse.class, DetailResponse.class})
    private int summaryInt;

    @JsonView({SummaryResponse.class, DetailResponse.class})
    private String summaryString;

    @JsonView({SummaryResponse.class, DetailResponse.class})
    private List<String> summaryList;


    @JsonView(DetailResponse.class)
    private int detailInt;

    @JsonView(DetailResponse.class)
    private String detailString;

    @JsonView(DetailResponse.class)
    private List<String> detailList;


    public static JsonViewResponse create() {
        return new JsonViewResponse(
                1, "normal", List.of("normalStr1", "normalStr2"),
                1, "summary", List.of("summaryStr1", "summaryStr2"),
                1, "detail", List.of("detailStr1", "detailStr2")
        );
    }


}
