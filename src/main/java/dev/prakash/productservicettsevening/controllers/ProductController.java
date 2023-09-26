package dev.prakash.productservicettsevening.controllers;

import dev.prakash.productservicettsevening.dtos.ProductDto;
import dev.prakash.productservicettsevening.models.Product;
import dev.prakash.productservicettsevening.services.ProductService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/products")
public class ProductController {
    private ProductService productService;
    public ProductController(ProductService productService) {
        this.productService = productService;
    }
    @GetMapping()
    public Product[] getAllProducts() {

        return productService.getAllProducts();
    }
    @GetMapping("/{productId}")
    public ResponseEntity<Product> getSingleProduct(@PathVariable("productId") Long productId) {
        MultiValueMap<String, String> headers = new LinkedMultiValueMap<>();
        headers.add("auth-token", "noaccessqwert");


        ResponseEntity<Product> response = new ResponseEntity(productService.getSingleProduct(productId),
                headers,
                HttpStatus.NOT_FOUND);
        return response;
//        GetSingleProductResponseDto responseDto = new GetSingleProductResponseDto();
//        responseDto.setProduct(productService.getSingleProduct(productId));
//        return responseDto;
    }
    @PostMapping()
    public ResponseEntity<Product> addProduct(@RequestBody ProductDto productDto){
        Product newProduct = productService.addProduct(productDto);
        ResponseEntity<Product> response = new ResponseEntity(newProduct, HttpStatus.CREATED);
        //return productService.addProduct();
        return response;
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
