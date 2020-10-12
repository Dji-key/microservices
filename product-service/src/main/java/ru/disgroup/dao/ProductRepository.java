package ru.disgroup.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.disgroup.entity.Product;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {

    List<Product> findAllByIdIn(List<Long> indices);
}
