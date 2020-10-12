package ru.disgroup.service;

import ru.disgroup.controller.specification.ArticleSpecification;
import ru.disgroup.dto.ArticleDto;

import java.util.List;

public interface ArticleService {

    List<ArticleDto> findAll(ArticleSpecification specification);

    List<ArticleDto> findByProductId(Long productId, Boolean fetchProduct);
}
