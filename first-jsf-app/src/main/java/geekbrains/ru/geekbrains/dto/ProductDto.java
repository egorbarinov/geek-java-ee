package geekbrains.ru.geekbrains.dto;

import geekbrains.ru.geekbrains.persist.Category;
import geekbrains.ru.geekbrains.persist.Product;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.math.BigDecimal;

@Data
@NoArgsConstructor
public class ProductDto implements Serializable {

    private Long id;
    private String name;
    private String description;
    private BigDecimal price;
    private Long categoryId;
    private String categoryName;

    public ProductDto(Product product) {
        id = product.getId();
        name = product.getName();
        description = product.getDescription();
        price = product.getPrice();
        Category category = product.getCategory();
        categoryId = (category == null) ? null : category.getId();
        categoryName = (category == null) ? null : category.getName();
    }
}
