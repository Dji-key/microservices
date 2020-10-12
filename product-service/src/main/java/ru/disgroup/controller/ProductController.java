package ru.disgroup.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.disgroup.dto.ProductDto;
import ru.disgroup.feign.ProductFeignClient;
import ru.disgroup.service.ProductService;

import java.util.List;

@RestController
@RequestMapping("/product")
public class ProductController implements ProductFeignClient {

    private ProductService productService;

    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @Override
    public List<ProductDto> getAll() {
        return productService.findAll();
    }

    @Override
    @GetMapping(GET_BY_ID)
    public ProductDto getById(@PathVariable Long id) {
        return productService.findById(id);
    }
}
