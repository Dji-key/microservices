package ru.disgroup.dao;

import org.springframework.data.domain.Sort;
import ru.disgroup.controller.specification.ProductSpecification;
import ru.disgroup.entity.Product;

import java.util.List;

public interface ProductDao {

    Product getById(Long id);

    List<Product> findAll(ProductSpecification specification, Sort sort);

    Product save(Product product);

    Product update(Product product);

    Long deleteById(Long id);
}
