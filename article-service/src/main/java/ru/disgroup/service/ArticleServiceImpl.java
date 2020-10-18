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

import java.util.HashMap;
import java.util.List;
import java.util.Map;
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
    public List<ArticleDto> findAll(ArticleSpecification specification, Sort sort, Boolean fetchProduct) {
        Map<Long, ProductDto> productsDtoToSet = new HashMap<>();
        return articleDao.findAll(specification, sort).stream()
                .map(article -> {
                    ArticleDto articleDto = mapper.map(article, ArticleDto.class);
                    if (fetchProduct) {
                        ProductDto respectiveProductDto = productsDtoToSet.computeIfAbsent(article.getProductId(),
                                (productId) -> {
                                    ProductDto foundProductDto = productFeignClient.getById(productId, false);
                                    productsDtoToSet.put(productId, foundProductDto);
                                    return foundProductDto;
                                });
                        articleDto.setProduct(respectiveProductDto);
                    }
                    return articleDto;
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
                        return articleDto.setProduct(productDto);
                    })
                    .collect(Collectors.toList());
        }

        return foundArticles.stream()
                .map(article -> mapper.map(article, ArticleDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public ArticleDto findById(Long id, Boolean fetchProduct) {
        Article foundArticle = articleDao.findById(id);
        ArticleDto resultArticleDto = mapper.map(foundArticle, ArticleDto.class);
        if (fetchProduct) {
            ProductDto foundProductDto = productFeignClient.getById(foundArticle.getProductId(), false);
            resultArticleDto.setProduct(foundProductDto);
        }
        return resultArticleDto;
    }

    @Override
    @Transactional
    public ArticleDto save(ArticleDto articleDto) {
        ProductDto foundProductDto = productFeignClient.getById(articleDto.getProduct().getId(), false);
        ArticleDto savedArticleDto = plainSave(articleDto, articleDto.getProduct().getId());
        return savedArticleDto.setProduct(foundProductDto);
    }

    @Override
    @Transactional
    public ArticleDto saveByProductId(Long productId, ArticleDto articleDto) {
        ProductDto foundProductDto = productFeignClient.getById(productId, false);
        ArticleDto savedArticleDto = plainSave(articleDto, productId);
        return savedArticleDto.setProduct(foundProductDto);
    }

    private ArticleDto plainSave(ArticleDto articleDto, Long productId) {
        Article articleToSave = mapper.map(articleDto, Article.class);
        articleToSave.setProductId(productId);
        Article savedArticle = articleDao.save(articleToSave.setProductId(productId));
        return mapper.map(savedArticle, ArticleDto.class);
    }

    @Override
    @Transactional
    public ArticleDto updateByProductId(Long productId, ArticleDto articleDto) {
        ProductDto foundProductDto = productFeignClient.getById(productId, false);
        Article articleToUpdate = mapper.map(articleDto, Article.class);
        Article updatedArticle = articleDao.update(articleToUpdate.setProductId(foundProductDto.getId()));
        ArticleDto updatedArticleDto = mapper.map(updatedArticle, ArticleDto.class);
        return updatedArticleDto.setProduct(foundProductDto);
    }

    @Override
    @Transactional
    public ArticleDto update(ArticleDto articleDto) {
        Article articleToUpdate = mapper.map(articleDto, Article.class);
        Article updatedArticle = articleDao.update(articleToUpdate);
        ProductDto foundProductDto = productFeignClient.getById(updatedArticle.getProductId(), false);
        ArticleDto updatedArticleDto = mapper.map(updatedArticle, ArticleDto.class);
        return updatedArticleDto.setProduct(foundProductDto);
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
