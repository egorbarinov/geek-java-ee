package ru.geekbrains.persist;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "products")
@Data
@NamedQueries({
        @NamedQuery(name = "findAllProducts", query = "from Product"),
        @NamedQuery(name = "deleteProductById", query = "delete from Product p where p.id = :id"),
        @NamedQuery(name = "countAllProducts", query = "select count(*) from Product")
})
@NoArgsConstructor
@AllArgsConstructor
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

}
