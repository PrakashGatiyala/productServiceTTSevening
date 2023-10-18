package dev.prakash.productservicettsevening;

import ch.qos.logback.core.net.SyslogOutputStream;
import dev.prakash.productservicettsevening.dtos.ProductDto;
import dev.prakash.productservicettsevening.models.Category;
import dev.prakash.productservicettsevening.models.Product;
import dev.prakash.productservicettsevening.repositories.CategoryRepository;
import dev.prakash.productservicettsevening.repositories.ProductRepository;
import dev.prakash.productservicettsevening.services.SelfCategoryService;
import dev.prakash.productservicettsevening.services.SelfProductService;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.junit.jupiter.api.Assertions;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.test.annotation.Commit;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@SpringBootTest
public class ProductTest {
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private CategoryRepository categoryRepository;
    @Autowired
    private SelfProductService selfProductService;
    @Autowired
    private SelfCategoryService selfCategoryService;
    @Autowired
    private EntityManager entityManager;

    @Autowired
    private PlatformTransactionManager transactionManager;
    @Transactional
    @Test
    @Commit // @Rollback(value = false)
    void savingProductAndCategory(){
        Category category = categoryRepository.findCategoryById(2L);
//        category.setName("Electronics9");
//        categoryRepository.save(category);
        Product product = new Product();
        product.setTitle("Iphone3");
        product.setPrice(100000);
        product.setCategory(category);
        productRepository.save(product);
    }
    @Test
    @Transactional
    void fetchTypesTest(){
        Product product = productRepository.findProductById(1L);
        System.out.println("Fetched Product");
        Category category= product.getCategory();
        String categoryName= category.getName();
        System.out.println(categoryName);

    }
    @Test
    void cascadePersistTest(){
        Category category = new Category();
        category.setName("Phones");
//        Category savedCategory= categoryRepository.save(category);

        Product product = new Product();
        product.setTitle("Iphone2");
        product.setPrice(200000);
        product.setCategory(category);
        productRepository.save(product);
    }
    @Test
    void cascadeDelete(){
        Product product = productRepository.findProductById(22L);
        productRepository.delete(product);
    }
    @Test
    @Transactional
    void getProductForCategory(){
//        Category category = categoryRepository.findCategoryById(1L);
//        System.out.println(category.getName());
        List<Category> categories = categoryRepository.findAllById(List.of(2L, 34L));
        // Select * from category where id in (2, 34)

        // Select * from Products where category_id = 2
        // Select * from Products where category_id = 34
        for(Category category1: categories){
            System.out.println("Category Name: " + category1.getName());
//            List<Product> products = category1.getProducts();
            for(Product product: category1.getProducts()){
                System.out.println(product.getTitle());
            }
        }
    }
    @Test
    @Transactional
    void getProductFor1Category(){
        Category category = categoryRepository.findCategoryById(2L);
        System.out.println("Category Name: " + category.getName());
        for(Product product: category.getProducts()){
            System.out.println(product.getTitle());
        }
    }
    @Test
    @Transactional
    @Commit
    void isWorkingFine(){
        List<Product> listOfProducts= selfProductService.getAllProducts();
        // Print all Products
        for(Product product: listOfProducts){
            System.out.println(product.getTitle());
        }
        // Get a single product and print it
        Optional<Product> product = selfProductService.getSingleProduct(8L);
        System.out.println(product.get().getTitle());

        // Create a ProductDto and add a product then print it
        ProductDto productDto = new ProductDto();
        productDto.setTitle("Iphone 12");
        productDto.setPrice(100000);
        productDto.setDescription("This is a new Iphone");
        productDto.setCategory("Electronics");
        productDto.setImage("https://fakestoreapi.com/img/81fPKd-2AYL._AC_SL1500_.jpg");
        Product newProduct = selfProductService.addProduct(productDto);
        // Print all properties of the product in single line
        System.out.println(newProduct.getId() + " " + newProduct.getTitle() + " " + newProduct.getPrice() + " "
                + newProduct.getDescription() + " " + newProduct.getCategory().getName() +
                " " + newProduct.getImageUrl());

        // Update a product and print it
        Product product1 = new Product();
        product1.setTitle("Iphone 12");
        product1.setPrice(100000);
        product1.setDescription("This is a new Iphone");
        product1.setCategory(new Category());
        product1.getCategory().setName("Electronics");
        product1.setImageUrl("https://fakestoreapi.com/img/81fPKd-2AYL._AC_SL1500_.jpg");
        Product updatedProduct = selfProductService.updateProduct(8L, product1);
        System.out.println(updatedProduct.getId() + " " + updatedProduct.getTitle() + " " + updatedProduct.getPrice() + " "
                + updatedProduct.getDescription() + " " + updatedProduct.getCategory().getName() +
                " " + updatedProduct.getImageUrl());

        // Delete a product and print its return value
        boolean isDeleted = selfProductService.deleteProduct(23L);
        System.out.println(isDeleted);

    }
    @Test
    @Transactional
    @Commit
    void isWorkingFineCategory(){
        // get all categories and print them
        List<Category> categories = selfCategoryService.getAllCategories();
        for(Category category: categories){
            System.out.println(category.getName());
        }
        // get all products in a category and print them
        List<Product> products = selfCategoryService.getProductsInCategory("Electronics");
        for(Product product: products){
            System.out.println(product.getTitle());
        }
    }


}
