package ru.disgroup.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.disgroup.controller.specification.ProductSpecification;
import ru.disgroup.dto.ProductDto;
import ru.disgroup.service.ProductService;

import java.util.List;

import static ru.disgroup.feign.ProductFeignClient.ALL;
import static ru.disgroup.feign.ProductFeignClient.BY_ID;
import static ru.disgroup.feign.ProductFeignClient.PATH;

@RestController
@RequestMapping(PATH)
public class ProductController {

    private ProductService productService;

    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping(ALL)
    public List<ProductDto> getAll(ProductSpecification specification, Sort sort) {
        return productService.findAll(specification, sort);
    }

    @GetMapping(BY_ID)
    public ProductDto getById(@PathVariable("id") Long id,
                              @RequestParam(value = "fetchArticles", required = false, defaultValue = "false") Boolean fetchArticles) {
        return productService.findById(id, fetchArticles);
    }
}
