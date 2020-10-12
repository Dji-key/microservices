package ru.disgroup.dao;

import ru.disgroup.entity.Article;

import java.util.List;

public interface ArticleDao {

    List<Article> findAll();

    List<Article> findByProductId(Long id);
}
