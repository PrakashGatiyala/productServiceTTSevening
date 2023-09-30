package dev.prakash.productservicettsevening.services;

import dev.prakash.productservicettsevening.models.Category;
import dev.prakash.productservicettsevening.models.Product;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

public interface CategoryService {
    Category[] getAllCategories();

    Product[] getProductsInCategory(String categoryName);
}
