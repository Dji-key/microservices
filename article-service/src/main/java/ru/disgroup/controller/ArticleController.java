package ru.disgroup.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.disgroup.controller.specification.ArticleSpecification;
import ru.disgroup.dto.ArticleDto;
import ru.disgroup.service.ArticleService;

import java.util.List;

import static ru.disgroup.feign.ArticleFeignClient.*;

/**
 * Имплементация {@link ru.disgroup.feign.ArticleFeignClient}
 */
@RestController
@RequestMapping(PATH)
public class ArticleController {

    private ArticleService articleService;

    @Autowired
    public ArticleController(ArticleService articleService) {
        this.articleService = articleService;
    }

    @GetMapping(GET_ALL)
    public List<ArticleDto> getAll(ArticleSpecification specification, Sort sort) {
        return articleService.findAll(specification, sort);
    }

    @GetMapping(GET_BY_ID)
    public ArticleDto getById(@PathVariable(ARTICLE_ID) Long id) {
        return articleService.findById(id);
    }

    @PutMapping
    ArticleDto updateById(@RequestBody ArticleDto articleDto) {
        return articleService.update(articleDto);
    }

    @DeleteMapping(DELETE_BY_ID)
    Long deleteById(@PathVariable(ARTICLE_ID) Long id) {
        return articleService.deleteById(id);
    }

    @GetMapping(GET_BY_PRODUCT_ID)
    public List<ArticleDto> getByProductId(@PathVariable(PRODUCT_ID) Long productId,
                                           @RequestParam(value = FETCH_PRODUCT, required = false, defaultValue = "true") Boolean fetchProduct) {
        return articleService.findByProductId(productId, fetchProduct);
    }

    @PostMapping(ADD_BY_PRODUCT_ID)
    public ArticleDto addByProductId(@PathVariable(PRODUCT_ID) Long productId,
                                     @RequestBody ArticleDto articleDto) {
        return articleService.saveByProductId(productId, articleDto);
    }

    @PutMapping(UPDATE_BY_PRODUCT_ID)
    ArticleDto updateByProductId(@PathVariable(PRODUCT_ID) Long productId,
                                 @RequestBody ArticleDto articleDto) {
        return articleService.updateByProductId(productId, articleDto);
    }

    @DeleteMapping(DELETE_BY_PRODUCT_ID)
    void deleteByProductId(@PathVariable(PRODUCT_ID) Long productId) {
        articleService.deleteByProductId(productId);
    }
}
