package ru.disgroup.service;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
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
    public List<ArticleDto> findAll(ArticleSpecification specification, Sort sort) {
        return articleDao.findAll(specification, sort).stream()
                .map(article -> {
                    ArticleDto articleDto = mapper.map(article, ArticleDto.class);
                    ProductDto productDto = productFeignClient.getById(article.getProductId(), false);
                    return articleDto.setProductDto(productDto);
                })
                .collect(Collectors.toList());
    }

    @Override
    public List<ArticleDto> findByProductId(Long productId, Boolean fetchProduct) {
        List<Article> foundArticles = articleDao.findByProductId(productId);

        if (fetchProduct) {
            ProductDto productDto = productFeignClient.getById(productId, false);
            return foundArticles.stream()
                    .map(article -> {
                        ArticleDto articleDto = mapper.map(article, ArticleDto.class);
                        return articleDto.setProductDto(productDto);
                    })
                    .collect(Collectors.toList());
        }

        return foundArticles.stream()
                .map(article -> mapper.map(article, ArticleDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public ArticleDto findById(Long id) {
        Article foundArticle = articleDao.findById(id);
        ProductDto foundProductDto = productFeignClient.getById(foundArticle.getProductId(), false);
        ArticleDto resultArticleDto = mapper.map(foundArticle, ArticleDto.class);
        return resultArticleDto.setProductDto(foundProductDto);
    }

    @Override
    @Transactional
    public ArticleDto saveByProductId(Long productId, ArticleDto articleDto) {
        ProductDto foundProductDto = productFeignClient.getById(productId, false);
        Article articleToSave = mapper.map(articleDto, Article.class);
        Article savedArticle = articleDao.save(articleToSave.setProductId(foundProductDto.getId()));
        ArticleDto savedArticleDto = mapper.map(savedArticle, ArticleDto.class);
        return savedArticleDto.setProductDto(foundProductDto);
    }

    @Override
    @Transactional
    public ArticleDto updateByProductId(Long productId, ArticleDto articleDto) {
        ProductDto foundProductDto = productFeignClient.getById(productId, false);
        Article articleToUpdate = mapper.map(articleDto, Article.class);
        Article updatedArticle = articleDao.update(articleToUpdate.setProductId(foundProductDto.getId()));
        ArticleDto updatedArticleDto = mapper.map(updatedArticle, ArticleDto.class);
        return updatedArticleDto.setProductDto(foundProductDto);
    }

    @Override
    @Transactional
    public ArticleDto update(ArticleDto articleDto) {
        Article articleToUpdate = mapper.map(articleDto, Article.class);
        Article updatedArticle = articleDao.update(articleToUpdate);
        ProductDto foundProductDto = productFeignClient.getById(updatedArticle.getProductId(), false);
        ArticleDto updatedArticleDto = mapper.map(updatedArticle, ArticleDto.class);
        return updatedArticleDto.setProductDto(foundProductDto);
    }

    @Override
    @Transactional
    public Long deleteById(Long id) {
        return articleDao.deleteById(id);
    }

    @Override
    @Transactional
    public void deleteByProductId(Long productId) {
        articleDao.deleteByProductId(productId);
    }
}
