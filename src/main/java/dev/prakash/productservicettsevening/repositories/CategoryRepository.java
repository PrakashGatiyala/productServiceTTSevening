package dev.prakash.productservicettsevening.repositories;

import dev.prakash.productservicettsevening.models.Category;
import dev.prakash.productservicettsevening.models.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CategoryRepository extends JpaRepository<Category, Long> {
    Category save(Category category);
    Category findCategoryById(Long id);
    List<Category> findAllByIdIn(List<Long> ids);
    List<Category> findAll();
}
