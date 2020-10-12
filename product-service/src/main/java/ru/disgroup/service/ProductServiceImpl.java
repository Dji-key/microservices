package ru.disgroup.service;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
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
    public ProductDto findById(Long id) {
        Product found = productDao.getById(id);
        return mapper.map(found, ProductDto.class);
    }

    @Override
    public List<ProductDto> findAll() {
        List<Product> products = productDao.findAll();
        return products.stream()
                .map(product -> {
                    ProductDto productDto = mapper.map(product, ProductDto.class);
                    List<ArticleDto> articles = articleFeignClient.getByProductId(product.getId(), true);
                    return productDto.setArticles(new HashSet<>(articles));
                })
                .collect(Collectors.toList());
    }
}
