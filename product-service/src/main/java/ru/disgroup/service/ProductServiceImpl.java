package ru.disgroup.service;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
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
        Product foundProduct = productDao.getById(id);
        ProductDto foundProductDto = mapper.map(foundProduct, ProductDto.class);
        if (fetchArticles) {
            List<ArticleDto> foundArticles = articleFeignClient.getByProductId(foundProduct.getId(), false);
            foundProductDto.setArticles(new HashSet<>(foundArticles));
        }
        return foundProductDto;
    }

    @Override
    public List<ProductDto> findAll(ProductSpecification specification, Sort sort) {
        List<Product> foundProducts = productDao.findAll(specification, sort);
        return foundProducts.stream()
                .map(product -> {
                    ProductDto productDto = mapper.map(product, ProductDto.class);
                    List<ArticleDto> foundArticlesDto = articleFeignClient.getByProductId(product.getId(), false);
                    return productDto.setArticles(new HashSet<>(foundArticlesDto));
                })
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public ProductDto save(ProductDto productDto) {
        Product productToSave = mapper.map(productDto, Product.class);
        Product savedProduct = productDao.save(productToSave);
        ProductDto savedProductDto = mapper.map(savedProduct, ProductDto.class);
        List<ArticleDto> foundArticles = articleFeignClient.getByProductId(savedProduct.getId(), false);
        return savedProductDto.setArticles(new HashSet<>(foundArticles));
    }

    @Override
    @Transactional
    public ProductDto update(ProductDto productDto) {
        Product productToUpdate = mapper.map(productDto, Product.class);
        Product updatedProduct = productDao.update(productToUpdate);
        ProductDto updatedProductDto = mapper.map(updatedProduct, ProductDto.class);
        List<ArticleDto> foundArticles = articleFeignClient.getByProductId(updatedProduct.getId(), false);
        return updatedProductDto.setArticles(new HashSet<>(foundArticles));
    }

    @Override
    @Transactional
    public Long deleteById(Long id) {
        Long deletedProductId = productDao.deleteById(id);
        articleFeignClient.deleteByProductId(deletedProductId);
        return deletedProductId;
    }
}
