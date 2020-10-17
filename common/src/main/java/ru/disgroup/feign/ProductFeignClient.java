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
import ru.disgroup.dto.ProductDto;
import ru.disgroup.feign.specification.ProductSpecification;

import java.util.List;

@FeignClient("product-service")
public interface ProductFeignClient {

    String PATH = "/product";
    String FETCH_ARTICLES = "fetchArticles";
    String PRODUCT_ID = "id";
    String GET_BY_ID = "/{" + PRODUCT_ID + "}";
    String DELETE_BY_ID = "/{" + PRODUCT_ID + "}";

    @GetMapping(PATH)
    List<ProductDto> getAll(ProductSpecification specification,
                            @RequestParam(value = FETCH_ARTICLES, required = false, defaultValue = "true") Boolean fetchArticles,
                            @RequestParam(value = "sort", required = false) Sort sort);

    @GetMapping(PATH + GET_BY_ID)
    ProductDto getById(@PathVariable(PRODUCT_ID) Long id,
                       @RequestParam(value = FETCH_ARTICLES, required = false, defaultValue = "true") Boolean fetchArticles);

    @PostMapping(PATH)
    ProductDto create(@RequestBody ProductDto productDto);

    @PutMapping(PATH)
    ProductDto update(@RequestBody ProductDto productDto);

    @DeleteMapping(PATH + DELETE_BY_ID)
    Long deleteById(@PathVariable(PRODUCT_ID) Long id);
}
