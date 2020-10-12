package ru.disgroup.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.disgroup.controller.specification.ArticleSpecification;
import ru.disgroup.dto.ArticleDto;
import ru.disgroup.service.ArticleService;

import java.util.List;

import static ru.disgroup.feign.ArticleFeignClient.PATH;
import static ru.disgroup.feign.ArticleFeignClient.ALL;
import static ru.disgroup.feign.ArticleFeignClient.BY_PRODUCT_ID;

@RestController
@RequestMapping(PATH)
public class ArticleController {

    private ArticleService articleService;

    @Autowired
    public ArticleController(ArticleService articleService) {
        this.articleService = articleService;
    }

    @GetMapping(ALL)
    public List<ArticleDto> getAll(ArticleSpecification specification) {
        return articleService.findAll(specification);
    }

    @GetMapping(BY_PRODUCT_ID)
    public List<ArticleDto> getByProductId(@PathVariable("productId") Long productId,
                                           @RequestParam(value = "fetchProduct", required = false, defaultValue = "false") Boolean fetchProduct) {
        return articleService.findByProductId(productId, fetchProduct);
    }
}
