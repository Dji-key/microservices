package ru.disgroup.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.disgroup.dto.ProductDto;
import ru.disgroup.service.ProductService;

import java.util.List;

import static ru.disgroup.feign.ProductFeignClient.ALL;
import static ru.disgroup.feign.ProductFeignClient.GET_BY_ID;

@RestController
@RequestMapping("/product")
public class ProductController {

    private ProductService productService;

    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping(ALL)
    public List<ProductDto> getAll() {
        return productService.findAll();
    }

    @GetMapping(GET_BY_ID)
    public ProductDto getById(@PathVariable Long id) {
        return productService.findById(id);
    }
}
