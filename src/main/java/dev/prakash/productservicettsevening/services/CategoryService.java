package dev.prakash.productservicettsevening.services;

import dev.prakash.productservicettsevening.models.Category;
import dev.prakash.productservicettsevening.models.Product;

import java.util.List;

public interface CategoryService {
    List<Category> getAllCategories();

    List<Product> getProductsInCategory(String categoryName);
}
