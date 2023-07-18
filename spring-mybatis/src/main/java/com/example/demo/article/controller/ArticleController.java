package com.example.demo.article.controller;

import com.example.demo.article.domain.Article;
import com.example.demo.article.mapper.ArticleMapper;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/articles")
@RequiredArgsConstructor
public class ArticleController {

    private final ArticleMapper articleMapper;

    @GetMapping("")
    public ResponseEntity<?> getArticle(@RequestParam(name = "id") Long id) {
        Article article = articleMapper.getArticle(id);
        if (article == null)
            return ResponseEntity.ok().build();

        return ResponseEntity.ok().body(article);
    }
}
