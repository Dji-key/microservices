package ru.disgroup.dao;

import ru.disgroup.controller.specification.ArticleSpecification;
import ru.disgroup.entity.Article;

import java.util.List;

public interface ArticleDao {

    List<Article> findAll(ArticleSpecification specification);

    List<Article> findByProductId(Long productId);
}
