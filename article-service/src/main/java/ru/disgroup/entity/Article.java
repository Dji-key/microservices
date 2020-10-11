package ru.disgroup.entity;

import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;
import java.util.Date;

@Entity
@Table(name = "article")
public class Article {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;

    @Column(name = "title")
    private String title;

    @Lob
    @Column(name = "content")
    private String content;

    @CreationTimestamp
    @Column(name = "creation_date")
    private Date creationDate;

    @Column(name = "product_id")
    private Long productId;

    public Long getId() {
        return id;
    }

    public Article setId(Long id) {
        this.id = id;
        return this;
    }

    public String getTitle() {
        return title;
    }

    public Article setTitle(String title) {
        this.title = title;
        return this;
    }

    public String getContent() {
        return content;
    }

    public Article setContent(String content) {
        this.content = content;
        return this;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public Article setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
        return this;
    }

    public Long getProductId() {
        return productId;
    }

    public Article setProductId(Long productId) {
        this.productId = productId;
        return this;
    }
}
