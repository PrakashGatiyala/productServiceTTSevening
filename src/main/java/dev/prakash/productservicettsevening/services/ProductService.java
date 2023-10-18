package dev.prakash.productservicettsevening.services;

import dev.prakash.productservicettsevening.dtos.ProductDto;
import dev.prakash.productservicettsevening.models.Product;

import java.util.List;
import java.util.Optional;

public interface ProductService {
    List<Product> getAllProducts();

     Optional<Product> getSingleProduct(Long productId);


    Product addProduct( ProductDto productDto);

    /*
    * Product Object has only those fields filled which needs to be updated
    * Everything else is null
    * */
    Product updateProduct( Long productId,  Product product);
    Product replaceProduct( Long productId,  Product product);

    boolean deleteProduct(Long productId);
}
