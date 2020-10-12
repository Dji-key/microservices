package ru.disgroup.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import ru.disgroup.dto.ProductDto;
import ru.disgroup.feign.specification.ProductSpecification;

import java.util.List;

@FeignClient("product-service")
public interface ProductFeignClient {

    String PATH = "/product";
    String ALL = "/all";
    String BY_ID = "/{id}";

    @GetMapping(PATH + ALL)
    List<ProductDto> getAll(ProductSpecification specification,
                            @RequestParam(value = "sort", required = false) Sort sort);

    @GetMapping(PATH + BY_ID)
    ProductDto getById(@PathVariable("id") Long id,
                       @RequestParam(value = "fetchArticles", required = false, defaultValue = "false") Boolean fetchArticles);
}
