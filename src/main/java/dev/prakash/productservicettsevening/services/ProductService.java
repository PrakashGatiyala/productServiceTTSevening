package dev.prakash.productservicettsevening.services;

import dev.prakash.productservicettsevening.dtos.ProductDto;
import org.springframework.web.bind.annotation.*;

public interface ProductService {
    String getAllProducts();

    String getSingleProduct(Long productId);

    String addProduct( ProductDto productDto);

    String updateProduct( Long productId,  ProductDto productDto);

    String deleteProduct(Long productId);
}
