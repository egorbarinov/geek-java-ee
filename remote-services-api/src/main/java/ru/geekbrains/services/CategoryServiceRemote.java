package ru.geekbrains.services;

import ru.geekbrains.dto.CategoryDto;

import java.util.List;

public interface CategoryServiceRemote {

    List<CategoryDto> findAll();

    CategoryDto findById(Long id);

    Long countAll();

}
