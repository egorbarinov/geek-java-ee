package ru.geekbrains.persist;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name="categories")
@Data
@NamedQueries({
        @NamedQuery(name = "findAllCategories", query = "from Category"),
        @NamedQuery(name = "deleteCategoryById", query = "delete from Category c where c.id = :id"),
        @NamedQuery(name = "countAllCategories", query = "select count(*) from Category")
})
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String name;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, mappedBy = "category")
    private List<Product> products;

}
