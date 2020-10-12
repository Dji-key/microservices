package ru.disgroup.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import ru.disgroup.dto.ProductDto;

@FeignClient("product-service")
public interface ProductFeignClient {

    final String PATH = "/product";
    final String GET_BY_ID = "/getById/{id}";

    @GetMapping(PATH + GET_BY_ID)
    ProductDto getById(@PathVariable("id") Long id);
}
