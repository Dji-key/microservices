package ru.disgroup.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ru.disgroup.entity.Product;

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
                .orElseThrow(() -> new RuntimeException("Да как так? Как так то? Непорядок"));
    }

    @Override
    public List<Product> findAll() {
        return productRepository.findAll();
    }
}
