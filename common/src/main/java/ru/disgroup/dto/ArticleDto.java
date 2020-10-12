package ru.disgroup.dto;

import java.util.Date;

public class ArticleDto {

    private Long id;

    private String title;

    private String content;

    private Date creationDate;

    private ProductDto productDto;

    public Long getId() {
        return id;
    }

    public ArticleDto setId(Long id) {
        this.id = id;
        return this;
    }

    public String getTitle() {
        return title;
    }

    public ArticleDto setTitle(String title) {
        this.title = title;
        return this;
    }

    public String getContent() {
        return content;
    }

    public ArticleDto setContent(String content) {
        this.content = content;
        return this;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public ArticleDto setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
        return this;
    }

    public ProductDto getProductDto() {
        return productDto;
    }

    public ArticleDto setProductDto(ProductDto productDto) {
        this.productDto = productDto;
        return this;
    }
}
