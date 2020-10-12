package ru.disgroup.service;

import org.springframework.data.domain.Sort;
import ru.disgroup.controller.specification.ProductSpecification;
import ru.disgroup.dto.ProductDto;

import java.util.List;

public interface ProductService {

    ProductDto findById(Long id, Boolean fetchArticles);

    List<ProductDto> findAll(ProductSpecification specification, Sort sort);
}
