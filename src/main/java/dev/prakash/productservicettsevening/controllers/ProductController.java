package dev.prakash.productservicettsevening.controllers;

import dev.prakash.productservicettsevening.dtos.ProductDto;
import dev.prakash.productservicettsevening.services.ProductService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/products")
public class ProductController {
    private ProductService productService;
    public ProductController(ProductService productService) {
        this.productService = productService;
    }
    @GetMapping()
    public String getAllProducts() {
        return "All Products";
    }
    @GetMapping("/{productId}")
    public String getSingleProduct(@PathVariable("productId") Long productId) {
        return "Product with " + productId;
    }
    @PostMapping()
    public String addProduct(@RequestBody ProductDto productDto){
        return "Add Product " + productDto;
    }
    @PutMapping("/{productId}")
    public String updateProduct(@PathVariable("productId") Long productId , @RequestBody ProductDto productDto) {
    return "Update Product with " + productId + " " + productDto;
    }
    @DeleteMapping("/{productId}")
    public String deleteProduct(@PathVariable("productId") Long productId) {
        return "Delete Product with " + productId;
    }
}
