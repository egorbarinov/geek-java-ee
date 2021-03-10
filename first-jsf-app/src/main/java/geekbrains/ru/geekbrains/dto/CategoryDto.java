package geekbrains.ru.geekbrains.dto;

import geekbrains.ru.geekbrains.persist.Category;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
public class CategoryDto implements Serializable {

    private Long id;
    private String name;

    public CategoryDto(Category category) {
        id = category.getId();
        name = category.getName();
    }
}
