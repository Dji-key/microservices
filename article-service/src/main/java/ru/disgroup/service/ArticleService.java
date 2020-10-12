package ru.disgroup.service;

import ru.disgroup.dto.ArticleDto;

import java.util.List;

public interface ArticleService {

    List<ArticleDto> findAll();

    List<ArticleDto> findByProductId(Long id, Boolean lazy);
}
