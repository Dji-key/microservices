package ru.disgroup.dao;

import ru.disgroup.entity.Product;

public interface ProductDao {

    Product getById(Long id);
}
