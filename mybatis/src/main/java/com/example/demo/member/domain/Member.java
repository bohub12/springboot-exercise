package com.example.demo.member.domain;

import com.example.demo.article.domain.Article;
import com.example.demo.member.dto.MemberSaveDto;
import com.example.demo.member.dto.MemberPatchDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Member {
    private String id;
    private String name;
    private List<Article> articleList;

    public static Member from(MemberSaveDto body) {
        return Member.builder()
                .id(body.getId())
                .name(body.getName())
                .build();
    }

    public static Member from(MemberPatchDto body) {
        return Member.builder()
                .id(body.getId())
                .name(body.getName())
                .build();
    }
}
