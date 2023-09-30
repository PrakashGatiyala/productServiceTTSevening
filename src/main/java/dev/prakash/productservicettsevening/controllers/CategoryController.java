package dev.prakash.productservicettsevening.controllers;

import dev.prakash.productservicettsevening.models.Category;
import dev.prakash.productservicettsevening.models.Product;
import dev.prakash.productservicettsevening.services.CategoryService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("products/")
public class CategoryController {
    private CategoryService categoryService;
    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }
    @GetMapping("/categories")
    public Category[] getAllCategories() {
        return categoryService.getAllCategories();
    }
    @GetMapping("category/{categoryName}")
    public Product[] getProductsInCategory(@PathVariable("categoryName") String categoryName) {
        return categoryService.getProductsInCategory(categoryName);
    }
}
