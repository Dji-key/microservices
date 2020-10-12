package ru.disgroup.service;

import ru.disgroup.dto.ProductDto;

import java.util.List;

public interface ProductService {

    ProductDto findById(Long id);

    List<ProductDto> findAll();
}
