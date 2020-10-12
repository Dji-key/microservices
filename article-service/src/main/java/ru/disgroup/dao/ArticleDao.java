package ru.disgroup.dao;

import org.springframework.data.domain.Sort;
import ru.disgroup.controller.specification.ArticleSpecification;
import ru.disgroup.entity.Article;

import java.util.List;

public interface ArticleDao {

    List<Article> findAll(ArticleSpecification specification, Sort sort);

    List<Article> findByProductId(Long productId);

    Article findById(Long id);
}
