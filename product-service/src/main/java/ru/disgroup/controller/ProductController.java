package ru.disgroup.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.disgroup.controller.specification.ProductSpecification;
import ru.disgroup.dto.ProductDto;
import ru.disgroup.service.ProductService;

import java.util.List;

import static ru.disgroup.feign.ProductFeignClient.DELETE_BY_ID;
import static ru.disgroup.feign.ProductFeignClient.FETCH_ARTICLES;
import static ru.disgroup.feign.ProductFeignClient.GET_BY_ID;
import static ru.disgroup.feign.ProductFeignClient.PATH;
import static ru.disgroup.feign.ProductFeignClient.PRODUCT_ID;

/**
 * Имплементация {@link ru.disgroup.feign.ProductFeignClient}
 */
@RestController
@RequestMapping(PATH)
public class ProductController {

    private ProductService productService;

    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping
    public List<ProductDto> getAll(ProductSpecification specification, Sort sort,
                                   @RequestParam(value = FETCH_ARTICLES, required = false, defaultValue = "false") Boolean fetchArticles) {
        return productService.findAll(specification, sort, fetchArticles);
    }

    @GetMapping(GET_BY_ID)
    public ProductDto getById(@PathVariable(PRODUCT_ID) Long id,
                              @RequestParam(value = FETCH_ARTICLES, required = false, defaultValue = "false") Boolean fetchArticles) {
        return productService.findById(id, fetchArticles);
    }

    @PostMapping
    ProductDto create(@RequestBody ProductDto productDto) {
        return productService.save(productDto);
    }

    @PutMapping
    ProductDto update(@RequestBody ProductDto productDto) {
        return productService.update(productDto);
    }

    @DeleteMapping(DELETE_BY_ID)
    Long deleteById(@PathVariable(PRODUCT_ID) Long id) {
        return productService.deleteById(id);
    }
}
