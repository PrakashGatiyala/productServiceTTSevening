package dev.prakash.productservicettsevening.services;

import org.springframework.stereotype.Service;

@Service
public class FakeStoreCategoryServiceImpl implements CategoryService{
    @Override
    public String getAllCategories() {
        return null;
    }

    @Override
    public String getProductsInCategory(String categoryName) {
        return null;
    }
}