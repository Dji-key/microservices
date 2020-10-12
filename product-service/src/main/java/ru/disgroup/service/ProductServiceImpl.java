package ru.disgroup.service;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.disgroup.dao.ProductDao;
import ru.disgroup.dto.ProductDto;
import ru.disgroup.entity.Product;

@Service
public class ProductServiceImpl implements ProductService {

    private ProductDao productDao;

    private ModelMapper mapper;

    @Autowired
    public ProductServiceImpl(ProductDao productDao, ModelMapper mapper) {
        this.productDao = productDao;
        this.mapper = mapper;
    }

    @Override
    public ProductDto findById(Long id) {
        Product found = productDao.getById(id);
        return mapper.map(found, ProductDto.class);
    }
}
