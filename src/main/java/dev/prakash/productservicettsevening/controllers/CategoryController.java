package dev.prakash.productservicettsevening.controllers;

import dev.prakash.productservicettsevening.models.Category;
import dev.prakash.productservicettsevening.models.Product;
import dev.prakash.productservicettsevening.services.CategoryService;
import dev.prakash.productservicettsevening.services.FakeStoreCategoryServiceImpl;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("products/")
public class CategoryController {
    private CategoryService categoryService;
    public CategoryController(@Qualifier("fakeStoreCategoryServiceImpl") CategoryService categoryService) {
        this.categoryService = categoryService;
    }
    @GetMapping("/categories")
    public List<Category> getAllCategories() {
        return categoryService.getAllCategories();
    }
    @GetMapping("category/{categoryName}")
    public List<Product> getProductsInCategory(@PathVariable("categoryName") String categoryName) {
        return categoryService.getProductsInCategory(categoryName);
    }
}
