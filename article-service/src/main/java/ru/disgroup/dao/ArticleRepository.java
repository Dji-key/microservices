package ru.disgroup.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.disgroup.entity.Article;

public interface ArticleRepository extends JpaRepository<Article, Long> {
}
