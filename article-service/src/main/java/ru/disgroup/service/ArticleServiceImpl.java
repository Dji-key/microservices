package ru.disgroup.service;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.disgroup.controller.specification.ArticleSpecification;
import ru.disgroup.dao.ArticleDao;
import ru.disgroup.dto.ArticleDto;
import ru.disgroup.dto.ProductDto;
import ru.disgroup.entity.Article;
import ru.disgroup.feign.ProductFeignClient;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ArticleServiceImpl implements ArticleService {

    private ArticleDao articleDao;

    private ModelMapper mapper;

    private ProductFeignClient productFeignClient;

    @Autowired
    public ArticleServiceImpl(ArticleDao articleDao, ModelMapper mapper, ProductFeignClient productFeignClient) {
        this.articleDao = articleDao;
        this.mapper = mapper;
        this.productFeignClient = productFeignClient;
    }

    @Override
    public List<ArticleDto> findAll(ArticleSpecification specification) {
        return articleDao.findAll(specification).stream()
                .map(article -> {
                    ArticleDto articleDto = mapper.map(article, ArticleDto.class);
                    ProductDto productDto = productFeignClient.getById(article.getProductId(), false);
                    return articleDto.setProductDto(productDto);
                })
                .collect(Collectors.toList());
    }

    @Override
    public List<ArticleDto> findByProductId(Long productId, Boolean fetchProduct) {
        List<Article> articles = articleDao.findByProductId(productId);

        if (fetchProduct) {
            ProductDto productDto = productFeignClient.getById(productId, false);
            return articles.stream()
                    .map(article -> {
                        ArticleDto articleDto = mapper.map(article, ArticleDto.class);
                        return articleDto.setProductDto(productDto);
                    })
                    .collect(Collectors.toList());
        }

        return articles.stream()
                .map(article -> mapper.map(article, ArticleDto.class))
                .collect(Collectors.toList());

    }
}
