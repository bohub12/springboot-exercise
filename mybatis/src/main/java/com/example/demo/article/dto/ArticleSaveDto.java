package com.example.demo.article.dto;

import com.example.demo.article.domain.ArticleContent;
import com.example.demo.article.domain.ArticleContentType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ArticleSaveDto {
    private String memberId;
    private String title;
    private String author;
    private String content;
    private ArticleContentType contentType;
}
