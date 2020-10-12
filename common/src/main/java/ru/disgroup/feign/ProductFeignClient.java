package ru.disgroup.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import ru.disgroup.dto.ProductDto;

import java.util.List;

@FeignClient("product-service")
public interface ProductFeignClient {

    String PATH = "/product";
    String ALL = "/all";
    String GET_BY_ID = "/getById/{id}";


    @GetMapping(PATH + ALL)
    List<ProductDto> getAll();

    @GetMapping(PATH + GET_BY_ID)
    ProductDto getById(@PathVariable("id") Long id);
}
