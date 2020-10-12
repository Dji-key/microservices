package ru.disgroup.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
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
    public List<Article> findAll() {
        return articleRepository.findAll();
    }

    @Override
    public List<Article> findByProductId(Long id) {
        return articleRepository.findAllByProductId(id);
    }
}
