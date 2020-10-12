package ru.disgroup.controller.specification;

import net.kaczmarzyk.spring.data.jpa.domain.LikeIgnoreCase;
import net.kaczmarzyk.spring.data.jpa.web.annotation.And;
import net.kaczmarzyk.spring.data.jpa.web.annotation.Spec;
import org.springframework.data.jpa.domain.Specification;
import ru.disgroup.entity.Article;

@And({
        @Spec(path = "title", spec = LikeIgnoreCase.class),
        @Spec(path = "content", spec = LikeIgnoreCase.class)
})
public interface ArticleSpecificationEntity extends Specification<Article> {
}
