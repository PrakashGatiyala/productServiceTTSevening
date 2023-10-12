package dev.prakash.productservicettsevening.repositories;

import dev.prakash.productservicettsevening.models.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Long> {
    Category save(Category category);
    Category findCategoryById(Long id);
}
