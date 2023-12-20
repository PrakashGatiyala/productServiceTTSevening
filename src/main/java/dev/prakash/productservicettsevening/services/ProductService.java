package dev.prakash.productservicettsevening.services;

import dev.prakash.productservicettsevening.dtos.ProductDto;
import dev.prakash.productservicettsevening.models.Product;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.Optional;

public interface ProductService {
    List<Product> getAllProducts();

     Optional<Product> getSingleProduct(Long productId);


    Product addProduct( ProductDto productDto);
    Page<Product> getProducts(String query, int numberOfProducts, int offset);

    /*
    * Product Object has only those fields filled which needs to be updated
    * Everything else is null
    * */
    Product updateProduct( Long productId,  Product product);
    Product replaceProduct( Long productId,  Product product);

    boolean deleteProduct(Long productId);
}
