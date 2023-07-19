package com.example.demo.article.domain;

import com.example.demo.article.dto.ArticleSaveDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.apache.ibatis.type.Alias;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Article {
    private Long id;
    private String title;
    private String author;
    private ArticleContent articleContent;

    public static Article from(ArticleSaveDto body) {
        return Article.builder()
                .title(body.getTitle())
                .author(body.getAuthor())
                .articleContent(new ArticleContent(
                        body.getContent(),
                        body.getContentType()
                ))
                .build();
    }
}
