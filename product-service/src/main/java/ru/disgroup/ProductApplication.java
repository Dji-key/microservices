package ru.disgroup;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.context.event.EventListener;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;
import ru.disgroup.dao.ProductRepository;
import ru.disgroup.entity.Product;

import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients
public class ProductApplication {

    @Autowired
    private ProductRepository productRepository;

    public static void main(String[] args) {
        SpringApplication.run(ProductApplication.class, args);
    }

    @EventListener(ApplicationReadyEvent.class)
    public void addFakeData() {
        List<Product> data = new ArrayList<>();
        data.add(new Product().setTitle("First").setDescription("Product with 3 articles").setCost(100));
        data.add(new Product().setTitle("Second").setDescription("Product with 2 articles").setCost(500));
        data.add(new Product().setTitle("Third").setDescription("Product with 1 article").setCost(100500));
        productRepository.saveAll(data);
    }
}
