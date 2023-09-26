package dev.prakash.productservicettsevening.services;

import dev.prakash.productservicettsevening.dtos.ProductDto;
import dev.prakash.productservicettsevening.models.Category;
import dev.prakash.productservicettsevening.models.Product;
import org.springframework.web.bind.annotation.*;

import java.util.List;

public interface ProductService {
    Product[] getAllProducts();

    Product getSingleProduct(Long productId);


    Product addProduct( ProductDto productDto);

    /*
    * Product Object has only those fields filled which needs to be updated
    * Everything else is null
    * */
    Product updateProduct( Long productId,  Product product);

    boolean deleteProduct(Long productId);
}
