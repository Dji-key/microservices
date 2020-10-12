package ru.disgroup.dao;

import ru.disgroup.controller.specification.ProductSpecification;
import ru.disgroup.entity.Product;

import java.util.List;

public interface ProductDao {

    Product getById(Long id);

    List<Product> findAll(ProductSpecification specification);
}
