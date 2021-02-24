package ru.geekbrains.repository;

import ru.geekbrains.persist.Category;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Named;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

@Named
@ApplicationScoped
public class CategoryRepository {

    private final Map<Long, Category> categoryMap = new ConcurrentHashMap<>();

    private final AtomicLong identity = new AtomicLong(0);

    @PostConstruct
    public void init(){
        this.saveOrUpdate(new Category(null, "Category 1"));
        this.saveOrUpdate(new Category(null, "Category 2"));
        this.saveOrUpdate(new Category(null, "Category 3"));
        this.saveOrUpdate(new Category(null, "Category 4"));
    }


    public List<Category> findAll() {
        return new ArrayList<>(categoryMap.values());
    }

    public Category findById(Long id) {
        return categoryMap.get(id);
    }

    public void saveOrUpdate(Category category) {
        if (category.getId() == null) {
            Long id = identity.incrementAndGet();
            category.setId(id);
        }
        categoryMap.put(category.getId(), category);
    }

    public void deleteById(Long id) {
        categoryMap.remove(id);
    }
}
