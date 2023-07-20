package com.example.demo.article.domain;

import com.example.demo.article.dto.ArticlePatchDto;
import com.example.demo.article.dto.ArticleSaveDto;
import lombok.*;
import org.apache.ibatis.type.Alias;

@Getter
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Article {
    private Long id;
    private String memberId;
    private String title;
    private String author;
    private ArticleContent articleContent;

    public static Article from(ArticleSaveDto body) {
        return Article.builder()
                .memberId(body.getMemberId())
                .title(body.getTitle())
                .author(body.getAuthor())
                .articleContent(new ArticleContent(
                        body.getContent(),
                        body.getContentType()
                ))
                .build();
    }

    public static Article from(ArticlePatchDto body) {
        return Article.builder()
                .id(body.getId())
                .title(body.getTitle())
                .author(null)
                .articleContent(new ArticleContent(
                        body.getContent(),
                        null
                ))
                .build();
    }
}
