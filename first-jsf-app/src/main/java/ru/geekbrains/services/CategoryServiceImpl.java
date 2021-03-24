package ru.geekbrains.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.geekbrains.dto.CategoryDto;
import ru.geekbrains.persist.Category;
import ru.geekbrains.persist.CategoryRepository;
import ru.geekbrains.rest.CategoryServiceRest;

import javax.ejb.EJB;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import java.util.List;
import java.util.stream.Collectors;

@Stateless
@Remote(CategoryServiceRemote.class)
public class CategoryServiceImpl implements CategoryService, CategoryServiceRemote, CategoryServiceRest {

    private static final Logger logger = LoggerFactory.getLogger(CategoryServiceImpl.class);

    @EJB
    private CategoryRepository categoryRepository;

    @Override
    public List<CategoryDto> findAll() {
        return categoryRepository.findAll().stream()
                .map(this::buildCategoryDto).collect(Collectors.toList());
    }

    @Override
    public CategoryDto findById(Long id) {
        Category category = categoryRepository.findById(id);
        if(category == null) return null;
        return buildCategoryDto(category);
    }

    @Override
    public CategoryDto findByName(String name) {
        Category category = categoryRepository.findByName(name);
        if(category == null) return null;
        return buildCategoryDto(category);
    }

    @Override
    public Long countAll() {
        return categoryRepository.countAll();
    }

    @Override
    public void insert(CategoryDto categoryDto) {
        if(categoryDto.getId() != null) {
            throw new IllegalArgumentException();
        }
        saveOrUpdate(categoryDto);
    }

    @Override
    public void update(CategoryDto categoryDto) {
        if(categoryDto.getId() == null) {
            throw new IllegalArgumentException();
        }
        saveOrUpdate(categoryDto);
    }

    @TransactionAttribute
    @Override
    public void saveOrUpdate(CategoryDto categoryDto) {
        categoryRepository.saveOrUpdate(new Category(categoryDto));
    }

    @TransactionAttribute
    @Override
    public void deleteById(Long id) {
        categoryRepository.deleteById(id);
    }

    public CategoryDto buildCategoryDto(Category category) {
        CategoryDto categoryDto = new CategoryDto();
        categoryDto.setId(category.getId());
        categoryDto.setName(category.getName());
        return categoryDto;
    }


}
