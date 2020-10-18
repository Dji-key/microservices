package ru.disgroup.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import ru.disgroup.dto.ArticleDto;
import ru.disgroup.feign.specification.ArticleSpecification;

import java.util.List;

@FeignClient("article-service")
public interface ArticleFeignClient {

    String PATH = "/article";
    String FETCH_PRODUCT = "fetchProduct";
    String ARTICLE_ID = "id";
    String PRODUCT_ID = "productId";
    String GET_BY_ID = "/{" + ARTICLE_ID + "}";
    String DELETE_BY_ID = "/{" + ARTICLE_ID + "}";
    String GET_BY_PRODUCT_ID = "/byProductId/{" + PRODUCT_ID +"}";
    String ADD_BY_PRODUCT_ID = "/byProductId/{" + PRODUCT_ID + "}";
    String UPDATE_BY_PRODUCT_ID = "/byProductId/{" + PRODUCT_ID + "}";
    String DELETE_BY_PRODUCT_ID = "/byProductId/{" + PRODUCT_ID + "}";

    @GetMapping(PATH)
    List<ArticleDto> getAll(ArticleSpecification specification,
                            @RequestParam(value = "sort", required = false) Sort sort,
                            @RequestParam(value = FETCH_PRODUCT, required = false, defaultValue = "false") Boolean fetchProduct);

    @GetMapping(PATH + GET_BY_ID)
    ArticleDto getById(@PathVariable(ARTICLE_ID) Long id,
                       @RequestParam(value = FETCH_PRODUCT, required = false, defaultValue = "false") Boolean fetchProduct);

    @PostMapping(PATH)
    ArticleDto create(@RequestBody ArticleDto articleDto);

    @PutMapping(PATH)
    ArticleDto updateById(@RequestBody ArticleDto articleDto);

    @DeleteMapping(PATH + DELETE_BY_ID)
    Long deleteById(@PathVariable(ARTICLE_ID) Long id);

    @GetMapping(PATH + GET_BY_PRODUCT_ID)
    List<ArticleDto> getByProductId(@PathVariable(PRODUCT_ID) Long id,
                                    @RequestParam(value = FETCH_PRODUCT, required = false, defaultValue = "false") Boolean fetchProduct);

    @PostMapping(PATH + ADD_BY_PRODUCT_ID)
    ArticleDto addByProductId(@PathVariable(PRODUCT_ID) Long productId,
                              @RequestBody ArticleDto articleDto);

    @PutMapping(PATH + UPDATE_BY_PRODUCT_ID)
    ArticleDto updateByProductId(@PathVariable(PRODUCT_ID) Long productId,
                                 @RequestBody ArticleDto articleDto);

    @DeleteMapping(PATH + DELETE_BY_PRODUCT_ID)
    void deleteByProductId(@PathVariable(PRODUCT_ID) Long productId);
}
