package dev.prakash.productservicettsevening.services;

import dev.prakash.productservicettsevening.dtos.ProductDto;
import org.springframework.stereotype.Service;

@Service
public class FakeStoreProductServiceImpl implements ProductService {

    @Override
    public String getAllProducts() {
        return null;
    }

    @Override
    public String getSingleProduct(Long productId) {
        return null;
    }

    @Override
    public String addProduct(ProductDto productDto) {
        return null;
    }

    @Override
    public String updateProduct(Long productId, ProductDto productDto) {
        return null;
    }

    @Override
    public String deleteProduct(Long productId) {
        return null;
    }
}
