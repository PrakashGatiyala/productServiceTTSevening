package dev.prakash.productservicettsevening.services;

import dev.prakash.productservicettsevening.Client.fakestoreapi.FakeStoreClient;
import dev.prakash.productservicettsevening.Client.fakestoreapi.FakeStoreProductDto;
import dev.prakash.productservicettsevening.models.Category;
import dev.prakash.productservicettsevening.models.Product;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FakeStoreCategoryServiceImpl implements CategoryService{
    FakeStoreClient fakeStoreClient;
    public FakeStoreCategoryServiceImpl(FakeStoreClient fakeStoreClient) {
        this.fakeStoreClient = fakeStoreClient;
    }
    @Override
    public List<Category> getAllCategories() {
          String[] listOfCategories = fakeStoreClient.getAllCategories();
            Category[] categories = new Category[listOfCategories.length];
            for(int i = 0; i < listOfCategories.length; i++){
                categories[i] = new Category();
                categories[i].setName(listOfCategories[i]);
            }

        return List.of(categories);
    }

    @Override
    public List<Product> getProductsInCategory(String categoryName) {
        FakeStoreProductDto[] fakeStoreProductDtos = fakeStoreClient.getProductsInCategory(categoryName);
        Product[] answer = new Product[fakeStoreProductDtos.length];
        for(int i=0; i< fakeStoreProductDtos.length; i++){
            answer[i] = new Product();
            answer[i].setId(fakeStoreProductDtos[i].getId());
            answer[i].setTitle(fakeStoreProductDtos[i].getTitle());
            answer[i].setDescription(fakeStoreProductDtos[i].getDescription());
            answer[i].setPrice(fakeStoreProductDtos[i].getPrice());
            answer[i].setImageUrl(fakeStoreProductDtos[i].getImage());
            Category category = new Category();
            category.setName(fakeStoreProductDtos[i].getCategory());
            answer[i].setCategory(category);
        }
        return List.of(answer);
    }
}
