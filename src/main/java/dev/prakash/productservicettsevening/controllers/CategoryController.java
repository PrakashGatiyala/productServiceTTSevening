package dev.prakash.productservicettsevening.controllers;

import dev.prakash.productservicettsevening.services.CategoryService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("products/categories")
public class CategoryController {
    private CategoryService categoryService;
    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }
    @GetMapping()
    public String getAllCategories() {
        return "All Categories";
    }
    @GetMapping("/{categoryName}")
    public String getProductsInCategory(@PathVariable("categoryName") String categoryName) {
        return "Products of " + categoryName;
    }
}
