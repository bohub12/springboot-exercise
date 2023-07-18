package com.example.demo.article.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ArticleContent {
    private ArticleContentType contentType;
    private String content;
}
