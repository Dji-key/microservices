package ru.disgroup.service;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.disgroup.dao.ArticleDao;
import ru.disgroup.dto.ArticleDto;
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
    public List<ArticleDto> findAll() {
        return articleDao.findAll().parallelStream()
                .map(article -> {
                    ArticleDto articleDto = mapper.map(article, ArticleDto.class);
                    articleDto.setProductDto(productFeignClient.getById(article.getProductId()));
                    return articleDto;
                })
                .collect(Collectors.toList());
    }
}
