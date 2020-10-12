package ru.disgroup.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import ru.disgroup.dto.ArticleDto;
import ru.disgroup.feign.specification.ArticleSpecification;

import java.util.List;

@FeignClient("article-service")
public interface ArticleFeignClient {

    String PATH = "/article";
    String ALL = "/all";
    String BY_ID = "/{id}";
    String BY_PRODUCT_ID = "/getByProductId/{productId}";

    @GetMapping(PATH + ALL)
    List<ArticleDto> getAll(ArticleSpecification specification,
                            @RequestParam(value = "sort", required = false) Sort sort);

    @GetMapping(PATH + BY_ID)
    ArticleDto getById(@PathVariable("id") Long id);

    @GetMapping(PATH + BY_PRODUCT_ID)
    List<ArticleDto> getByProductId(@PathVariable("productId") Long id,
                                    @RequestParam(value = "fetchProduct", required = false, defaultValue = "false") Boolean fetchProduct);
}
