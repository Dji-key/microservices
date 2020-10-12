package ru.disgroup.service;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import ru.disgroup.controller.specification.ProductSpecification;
import ru.disgroup.dao.ProductDao;
import ru.disgroup.dto.ArticleDto;
import ru.disgroup.dto.ProductDto;
import ru.disgroup.entity.Product;
import ru.disgroup.feign.ArticleFeignClient;

import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductServiceImpl implements ProductService {

    private ProductDao productDao;

    private ModelMapper mapper;

    private ArticleFeignClient articleFeignClient;

    @Autowired
    public ProductServiceImpl(ProductDao productDao, ModelMapper mapper, ArticleFeignClient articleFeignClient) {
        this.productDao = productDao;
        this.mapper = mapper;
        this.articleFeignClient = articleFeignClient;
    }

    @Override
    public ProductDto findById(Long id, Boolean fetchArticles) {
        Product found = productDao.getById(id);
        ProductDto productDto = mapper.map(found, ProductDto.class);
        if (fetchArticles) {
            List<ArticleDto> articles = articleFeignClient.getByProductId(found.getId(), false);
            productDto.setArticles(new HashSet<>(articles));
        }
        return productDto;
    }

    @Override
    public List<ProductDto> findAll(ProductSpecification specification, Sort sort) {
        List<Product> products = productDao.findAll(specification, sort);
        return products.stream()
                .map(product -> {
                    ProductDto productDto = mapper.map(product, ProductDto.class);
                    List<ArticleDto> articles = articleFeignClient.getByProductId(product.getId(), false);
                    return productDto.setArticles(new HashSet<>(articles));
                })
                .collect(Collectors.toList());
    }
}
