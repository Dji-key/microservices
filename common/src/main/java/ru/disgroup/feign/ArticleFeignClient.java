package ru.disgroup.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import ru.disgroup.dto.ArticleDto;

import java.util.List;

@FeignClient("article-service")
public interface ArticleFeignClient {

    String PATH = "/article";
    String ALL = "/all";
    String ALL_BY_SPEC = "/allBySpec";
    String GET_BY_PRODUCT_ID = "/getByProductId/{productId}";

    @GetMapping(PATH + ALL)
    List<ArticleDto> getAll();

    @GetMapping(PATH + GET_BY_PRODUCT_ID)
    List<ArticleDto> getByProductId(@PathVariable("productId") Long id, @RequestParam("lazy") Boolean lazy);
}
