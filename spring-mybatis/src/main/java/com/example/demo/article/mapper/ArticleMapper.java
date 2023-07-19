package com.example.demo.article.mapper;

import com.example.demo.article.domain.Article;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface ArticleMapper {

//    @Select("SELECT * FROM ARTICLE WHERE id = #{id}")
    Article getArticle(@Param("id") Long id);
}
