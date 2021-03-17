package ru.geekbrains.services;

import ru.geekbrains.dto.CategoryDto;

import javax.ejb.Remote;
import java.util.List;

@Remote
public interface CategoryServiceRemote {

    List<CategoryDto> findAll();

    CategoryDto findById(Long id);

    Long countAll();

}
