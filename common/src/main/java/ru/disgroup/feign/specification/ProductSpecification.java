package ru.disgroup.feign.specification;

import net.kaczmarzyk.spring.data.jpa.domain.Equal;
import net.kaczmarzyk.spring.data.jpa.domain.LikeIgnoreCase;
import net.kaczmarzyk.spring.data.jpa.web.annotation.And;
import net.kaczmarzyk.spring.data.jpa.web.annotation.Spec;
import org.springframework.data.jpa.domain.Specification;
import ru.disgroup.dto.ProductDto;

@And({
        @Spec(path = "title", spec = LikeIgnoreCase.class),
        @Spec(path = "description", spec = LikeIgnoreCase.class),
        @Spec(path = "cost", spec = Equal.class)
})
public interface ProductSpecification extends Specification<ProductDto> {
}
