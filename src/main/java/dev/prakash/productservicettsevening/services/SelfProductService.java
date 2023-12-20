package dev.prakash.productservicettsevening.services;

import dev.prakash.productservicettsevening.dtos.ProductDto;
import dev.prakash.productservicettsevening.models.Category;
import dev.prakash.productservicettsevening.models.Product;
import dev.prakash.productservicettsevening.repositories.ProductRepository;
import org.springframework.context.annotation.Primary;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Primary
public class SelfProductService implements ProductService {

    private ProductRepository productRepository;
    public SelfProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }
    @Override
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    @Override
    public Page<Product> getProducts(String query, int numberOfProducts, int offset){
        // productRepository.executeQuery(select * from producst limit numberOfProducsts, offset offset)
//        Page<Product> products = productRepository.findAll(
//                PageRequest.of( offset/numberOfProducts,  numberOfProducts, Sort.Direction.valueOf("ASC"), query )
//        );
        Page<Product> products = productRepository.findAllByTitleContaining(
                query,
                PageRequest.of( offset/numberOfProducts,  numberOfProducts )
        );
        return products;
    }

    @Override
    public Optional<Product> getSingleProduct(Long productId) {

        return Optional.ofNullable(productRepository.findProductById(productId));
    }

    @Override
    public Product addProduct(ProductDto productDto) {
        Product product = new Product();
        product.setTitle(productDto.getTitle());
        product.setDescription(productDto.getDescription());
        product.setPrice(productDto.getPrice());
        product.setImageUrl(productDto.getImage());
        Category category = new Category();
        category.setName(productDto.getCategory());
        product.setCategory(category);
        return productRepository.save(product);

    }

    @Override
    public Product updateProduct(Long productId, Product product) {
         productRepository.updateProductByIdAndProduct(productId, product.getTitle(),
                product.getDescription(), product.getPrice(), product.getImageUrl());
         return productRepository.findProductById(productId);
    }

    @Override
    public Product replaceProduct(Long productId, Product product) {
        // Same as updateProduct
        return null;
    }

    @Override
    public boolean deleteProduct(Long productId) {
        Optional<Product> productOptional1 = Optional.ofNullable(productRepository.findProductById(productId));
        if(productOptional1.isEmpty()){
            return true;
        }
        productRepository.deleteProductById(productId);
        // confirm that product gets deleted then send boolean value
        Optional<Product> productOptional = Optional.ofNullable(productRepository.findProductById(productId));
        if(productOptional.isEmpty()){
            return true;
        }
        return false;
    }

}
