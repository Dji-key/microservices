package ru.disgroup.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Repository;
import ru.disgroup.controller.specification.ArticleSpecification;
import ru.disgroup.entity.Article;

import java.util.List;

@Repository
public class ArticleDaoImpl implements ArticleDao {

    private ArticleRepository articleRepository;

    @Autowired
    public ArticleDaoImpl(ArticleRepository articleRepository) {
        this.articleRepository = articleRepository;
    }

    @Override
    public List<Article> findAll(ArticleSpecification specification, Sort sort) {
        return articleRepository.findAll(specification, sort);
    }

    @Override
    public List<Article> findByProductId(Long productId) {
        return articleRepository.findAllByProductId(productId);
    }
}
