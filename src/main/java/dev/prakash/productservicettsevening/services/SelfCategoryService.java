package dev.prakash.productservicettsevening.services;

import dev.prakash.productservicettsevening.models.Category;
import dev.prakash.productservicettsevening.models.Product;
import dev.prakash.productservicettsevening.repositories.CategoryRepository;
import dev.prakash.productservicettsevening.repositories.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class SelfCategoryService implements CategoryService {
    private CategoryRepository categoryRepository;
    private ProductRepository productRepository;

    public SelfCategoryService(CategoryRepository categoryRepository,
                               ProductRepository productRepository) {
        this.categoryRepository = categoryRepository;
        this.productRepository = productRepository;
    }
    @Override
    public List<Category> getAllCategories() {
        return categoryRepository.findAll();
    }

    @Override
    public List<Product> getProductsInCategory(String categoryName) {
        return productRepository.findProductsByName(categoryName);

    }
}
