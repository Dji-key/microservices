package ru.disgroup.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Repository;
import ru.disgroup.controller.specification.ArticleSpecification;
import ru.disgroup.entity.Article;
import ru.disgroup.exception.ArticleAlreadyExists;
import ru.disgroup.exception.ArticleNotFoundException;

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

    @Override
    public Article findById(Long id) {
        return articleRepository.findById(id).orElseThrow(() -> new ArticleNotFoundException(id));
    }

    @Override
    public Article save(Article article) {
        if (article.getId() != null) {
            throw new ArticleAlreadyExists(article.getId());
        }
        return articleRepository.save(article);
    }

    @Override
    public Article update(Article article) {
        if (article.getId() == null) {
            throw new ArticleNotFoundException("Update статьи с id = null");
        }
        return articleRepository.save(article);
    }

    @Override
    public Long deleteById(Long id) {
        articleRepository.deleteById(id);
        return id;
    }

    @Override
    public void deleteByProductId(Long productId) {
        articleRepository.deleteByProductId(productId);
    }
}
