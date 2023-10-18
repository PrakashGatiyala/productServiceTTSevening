package dev.prakash.productservicettsevening.repositories;

import dev.prakash.productservicettsevening.models.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {
    Product save(Product product);
    Product findProductById(Long id);
    List<Product> findAll();

    // Create a custom query function which takes Product Id and Product object as input and returns Product object as output
    @Modifying
    @Query("update Product p set p.title = ?2, p.description = ?3, p.price = ?4, p.imageUrl = ?5 where p.id = ?1")
     void updateProductByIdAndProduct(Long id, String title, String description, Double price, String imageUrl);

   // Create a query to Delete a product by id
    void deleteProductById(Long id);

    // Create a query to find all products by category name
    @Modifying
    @Query("select p from Product p where p.category.name = ?1")
    List<Product> findProductsByName(String name);



}
//
//    List<Product> findByTitleLike(String title);
//    List<Product> findByTitleIgnoreCaseStartingWith(String title);
//    // List<Product> findByImageUrlIsNullOOrderByIdDesc();
//    List<Product> findByTitleLikeIgnoreCase(String title);
// List<Product> findAllByIsisPublicTrue();

// More detailed JPA queries are in following link
// https://docs.spring.io/spring-data/jpa/docs/current/reference/html/#jpa.query-methods
