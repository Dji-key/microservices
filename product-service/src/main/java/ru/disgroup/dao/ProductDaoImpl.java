package ru.disgroup.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Repository;
import ru.disgroup.controller.specification.ProductSpecification;
import ru.disgroup.entity.Product;
import ru.disgroup.exception.ProductNotFoundException;

import java.text.MessageFormat;
import java.util.List;

@Repository
public class ProductDaoImpl implements ProductDao {

    private ProductRepository productRepository;

    @Autowired
    public ProductDaoImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public Product getById(Long id) {
        return productRepository.findById(id)
                .orElseThrow(() -> new ProductNotFoundException(id));
    }

    @Override
    public List<Product> findAll(ProductSpecification specification, Sort sort) {
        return productRepository.findAll(specification, sort);
    }
}
