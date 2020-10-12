package ru.disgroup.service;

import ru.disgroup.dto.ProductDto;

public interface ProductService {

    ProductDto findById(Long id);
}
