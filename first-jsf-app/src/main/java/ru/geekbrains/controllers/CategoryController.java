package ru.geekbrains.controllers;

import ru.geekbrains.dto.CategoryDto;
import ru.geekbrains.services.CategoryService;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.event.ComponentSystemEvent;
import javax.inject.Named;
import java.io.Serializable;
import java.util.List;

@Named
@SessionScoped
public class CategoryController implements Serializable {

    @EJB
    private CategoryService categoryService;

    private CategoryDto category;

    private List<CategoryDto> categories;

    @PostConstruct
    public void init() {
        categories = categoryService.findAll();
    }

    public void preloadData(ComponentSystemEvent componentSystemEvent) {
        init();
    }

    public CategoryDto getCategory() {
        return category;
    }

    public void setCategory(CategoryDto categoryDto) {
        this.category = categoryDto;
    }

    public String createCategory() {
        this.category = new CategoryDto();
        return "/category_form.xhtml?faces-redirect=true";
    }

    public List<CategoryDto> getAllCategories() {
        return categories;
    }

    public String editCategory(CategoryDto categoryDto) {
        this.category = categoryDto;
        return "/category_form.xhtml?faces-redirect=true";
    }

    public void deleteCategory(CategoryDto categoryDto) {
        categoryService.deleteById(categoryDto.getId());
    }

    public String saveCategory() {
        categoryService.saveOrUpdate(category);
        return "/category.xhtml?faces-redirect=true";
    }
}
