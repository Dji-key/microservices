package ru.disgroup.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import ru.disgroup.dto.ArticleDto;

import java.util.List;

@FeignClient("article-service")
public interface ArticleFeignClient {

    final String PATH = "/article";
    final String ALL = "/all";

    @GetMapping(PATH + ALL)
    List<ArticleDto> getAll();
}
