package dev.prakash.productservicettsevening.controllers;

import dev.prakash.productservicettsevening.dtos.ProductDto;
import dev.prakash.productservicettsevening.exceptions.NotFoundException;
import dev.prakash.productservicettsevening.models.Category;
import dev.prakash.productservicettsevening.models.Product;
import dev.prakash.productservicettsevening.services.FakeStoreProductServiceImpl;
import dev.prakash.productservicettsevening.services.ProductService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/products")
public class ProductController {
    private ProductService productService;
    public ProductController(@Qualifier("fakeStoreProductServiceImpl") ProductService productService) {
        this.productService = productService;
    }
    @GetMapping()
    public List<Product> getAllProducts() {

        return productService.getAllProducts();
    }
    @GetMapping("/{productId}")
    public ResponseEntity<Product> getSingleProduct(@PathVariable("productId") Long productId) throws NotFoundException {
        MultiValueMap<String, String> headers = new LinkedMultiValueMap<>();
        headers.add("auth-token", "noaccessqwert");
        Optional<Product> productOptional = productService.getSingleProduct(productId);
        if(productOptional.isEmpty()){
            throw new NotFoundException("Product with id "+productId+" not found");
        }



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
    @PatchMapping("/{productId}")
    public Product updateProduct(@PathVariable("productId") Long productId , @RequestBody ProductDto productDto) {
        Product product = new Product();
        product.setId(productId);
        product.setTitle(productDto.getTitle());
        product.setDescription(productDto.getDescription());
        product.setPrice(productDto.getPrice());
        product.setCategory(new Category());
        product.getCategory().setName(productDto.getCategory());
       product.setImageUrl(productDto.getImage());

    return productService.updateProduct(productId, product);
    }
    @PutMapping("/{productId}")
    public Product replaceProduct(@PathVariable("productId") Long productId , @RequestBody ProductDto productDto) {
        Product product = new Product();
        product.setId(productId);
        product.setTitle(productDto.getTitle());
        product.setDescription(productDto.getDescription());
        product.setPrice(productDto.getPrice());
        product.setCategory(new Category());
        product.getCategory().setName(productDto.getCategory());
        product.setImageUrl(productDto.getImage());

        return productService.replaceProduct(productId, product);
    }
    @DeleteMapping("/{productId}")
    public boolean deleteProduct(@PathVariable("productId") Long productId) {
        return productService.deleteProduct(productId);
    }
//    @ExceptionHandler(NotFoundException.class)
//    public ResponseEntity<ErrorResponseDto> handleNotFoundException(NotFoundException exception){
//        ErrorResponseDto errorResponseDto = new ErrorResponseDto();
//        errorResponseDto.setErrorMessage(exception.getMessage());
//        return new ResponseEntity<>(errorResponseDto, HttpStatus.NOT_FOUND);
//    }
}
