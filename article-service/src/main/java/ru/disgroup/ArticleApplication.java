package ru.disgroup;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.event.EventListener;
import ru.disgroup.dao.ArticleRepository;
import ru.disgroup.entity.Article;

import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients
public class ArticleApplication {

    @Autowired
    private ArticleRepository articleRepository;

    public static void main(String[] args) {
        SpringApplication.run(ArticleApplication.class, args);
    }

    @EventListener(ApplicationReadyEvent.class)
    public void addFakeData() {
        List<Article> data = new ArrayList<>();
        data.add(new Article().setTitle("First").setContent("Article of first product").setProductId(1L));
        data.add(new Article().setTitle("Second").setContent("Article of first product").setProductId(1L));
        data.add(new Article().setTitle("Third").setContent("Article of first product").setProductId(1L));
        data.add(new Article().setTitle("First").setContent("Article of second product").setProductId(2L));
        data.add(new Article().setTitle("Second").setContent("Article of second product").setProductId(2L));
        data.add(new Article().setTitle("First").setContent("Article of third product").setProductId(3L));
        articleRepository.saveAll(data);
    }
}
