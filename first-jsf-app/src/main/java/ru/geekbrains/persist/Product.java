package ru.geekbrains.persist;

import lombok.Data;
import lombok.NoArgsConstructor;
import ru.geekbrains.dto.ProductDto;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "products")
@Data
@NamedQueries({
        @NamedQuery(name = "findAllProducts", query = "from Product"),
        @NamedQuery(name = "findProductByName", query = "from Product p where p.name = :name"),
        @NamedQuery(name = "findProductByCategory", query = "from Product p where p.category.id = :category_id"),
        @NamedQuery(name = "deleteProductById", query = "delete from Product p where p.id = :id"),
        @NamedQuery(name = "countAllProducts", query = "select count(*) from Product")
})
@NoArgsConstructor
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String name;

    @Column(length = 1024)
    private String description;

    @Column
    private BigDecimal price;

    @ManyToOne
    private Category category;

    public Product(Long id, String name, String description, BigDecimal price) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.price = price;
    }

    public Product(ProductDto productDto, Category category) {
        this(productDto.getId(), productDto.getName(), productDto.getDescription(), productDto.getPrice());
        this.category = category;
    }
}
