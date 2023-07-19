package com.example.demo.article.controller;

import com.example.demo.article.domain.Article;
import com.example.demo.article.dto.ArticlePatchDto;
import com.example.demo.article.dto.ArticleSaveDto;
import com.example.demo.article.mapper.ArticleMapper;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/articles")
@RequiredArgsConstructor
public class ArticleController {

    private final ArticleMapper articleMapper;

    @GetMapping("")
    public ResponseEntity<?> getArticle(@RequestParam(name = "id") Long id) {
        Article article = articleMapper.getArticle(id);
        if (article == null)
            return ResponseEntity.notFound().build();

        return ResponseEntity.ok().body(article);
    }

    @PostMapping("")
    public ResponseEntity<?> saveArticle(@RequestBody ArticleSaveDto body) {
        articleMapper.saveArticle(Article.from(body));

        return ResponseEntity.ok().build();
    }

    @PatchMapping("")
    public ResponseEntity<?> updateArticle(@RequestBody ArticlePatchDto body) {
        Article articleToUpdate = Article.from(body);

        articleMapper.updateArticle(articleToUpdate);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("")
    public ResponseEntity<?> deleteArticle(@RequestParam(name = "id") Long id) {
        articleMapper.deleteArticle(id);
        return ResponseEntity.ok().build();
    }
}
