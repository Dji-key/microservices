package ru.disgroup.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.disgroup.dto.ArticleDto;
import ru.disgroup.feign.ArticleFeignClient;
import ru.disgroup.service.ArticleService;

import java.util.List;

@RestController
@RequestMapping("/article")
public class ArticleController implements ArticleFeignClient {

    private ArticleService articleService;

    @Autowired
    public ArticleController(ArticleService articleService) {
        this.articleService = articleService;
    }

    @Override
    @GetMapping(ALL)
    public List<ArticleDto> getAll() {
        return articleService.findAll();
    }
}
