package dev.prakash.productservicettsevening;

import ch.qos.logback.core.net.SyslogOutputStream;
import dev.prakash.productservicettsevening.models.Category;
import dev.prakash.productservicettsevening.models.Product;
import dev.prakash.productservicettsevening.repositories.CategoryRepository;
import dev.prakash.productservicettsevening.repositories.ProductRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.junit.jupiter.api.Assertions;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;
import java.util.ArrayList;
import java.util.List;

@SpringBootTest
public class ProductTest {
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private CategoryRepository categoryRepository;
    @Autowired
    private EntityManager entityManager;

    @Autowired
    private PlatformTransactionManager transactionManager;
    @Transactional
    @Test
    void savingProductAndCategory(){
        Category category = new Category();
        category.setName("Electronics7");
//        categoryRepository.save(category);
        Product product = new Product();
        product.setTitle("Iphone8");
        product.setPrice(100000);
        product.setCategory(category);
        productRepository.save(product);

        DefaultTransactionDefinition def = new DefaultTransactionDefinition();
        TransactionStatus status = transactionManager.getTransaction(def);
        transactionManager.commit(status);

        // Flush the EntityManager to save the data to the database
        entityManager.flush();

        System.out.println(product.getId());
        System.out.println(category.getId());
        // Verify that the data was saved correctly
        Category savedCategory = categoryRepository.findById(category.getId()).orElse(null);
        Product savedProduct = productRepository.findById(product.getId()).orElse(null);

        Assertions.assertNotNull(savedCategory);
        Assertions.assertNotNull(savedProduct);
        Assertions.assertEquals("Electronics7", savedCategory.getName());
        Assertions.assertEquals("Iphone8", savedProduct.getTitle());
        System.out.println("Saved Category: " + savedCategory.getName());
        System.out.println("Saved Product: " + savedProduct.getTitle());
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
}
