package dev.prakash.productservicettsevening.repositories;

import dev.prakash.productservicettsevening.models.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
    Product save(Product product);
}