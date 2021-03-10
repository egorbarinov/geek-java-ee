package geekbrains.ru.geekbrains.sevice;

import geekbrains.ru.geekbrains.dto.CategoryDto;

import javax.ejb.Remote;
import java.util.List;

@Remote
public interface CategoryServiceRemote {

    List<CategoryDto> findAll();

    CategoryDto findById(Long id);

    Long countAll();

}
