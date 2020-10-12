package ru.disgroup.dto;

import java.util.HashSet;
import java.util.Set;

public class ProductDto {

    private Long id;

    private String title;

    private String description;

    private Integer cost;

    private Set<ArticleDto> articles = new HashSet<>();

    public Long getId() {
        return id;
    }

    public ProductDto setId(Long id) {
        this.id = id;
        return this;
    }

    public String getTitle() {
        return title;
    }

    public ProductDto setTitle(String title) {
        this.title = title;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public ProductDto setDescription(String description) {
        this.description = description;
        return this;
    }

    public Integer getCost() {
        return cost;
    }

    public ProductDto setCost(Integer cost) {
        this.cost = cost;
        return this;
    }

    public Set<ArticleDto> getArticles() {
        return articles;
    }

    public ProductDto setArticles(Set<ArticleDto> articles) {
        this.articles = articles;
        return this;
    }
}
