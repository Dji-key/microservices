package ru.disgroup.service;

import org.springframework.data.domain.Sort;
import ru.disgroup.controller.specification.ArticleSpecification;
import ru.disgroup.dto.ArticleDto;

import java.util.List;

public interface ArticleService {

    List<ArticleDto> findAll(ArticleSpecification specification, Sort sort);

    List<ArticleDto> findByProductId(Long productId, Boolean fetchProduct);

    ArticleDto findById(Long id);

    ArticleDto saveByProductId(Long productId, ArticleDto articleDto);

    ArticleDto updateByProductId(Long productId, ArticleDto articleDto);

    ArticleDto update(ArticleDto articleDto);

    Long deleteById(Long id);

    void deleteByProductId(Long productId);
}
