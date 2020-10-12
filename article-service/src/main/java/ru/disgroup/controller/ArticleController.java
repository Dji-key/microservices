package ru.disgroup.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.disgroup.dto.ArticleDto;
import ru.disgroup.service.ArticleService;

import java.util.List;

import static ru.disgroup.feign.ArticleFeignClient.ALL;
import static ru.disgroup.feign.ArticleFeignClient.GET_BY_PRODUCT_ID;

@RestController
@RequestMapping("/article")
public class ArticleController {

    private ArticleService articleService;

    @Autowired
    public ArticleController(ArticleService articleService) {
        this.articleService = articleService;
    }

    @GetMapping(ALL)
    public List<ArticleDto> getAll() {
        return articleService.findAll();
    }

    @GetMapping(GET_BY_PRODUCT_ID)
    public List<ArticleDto> getByProductId(Long id, Boolean lazy) {
        return articleService.findByProductId(id, lazy);
    }
}
