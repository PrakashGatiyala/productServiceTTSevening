package dev.prakash.productservicettsevening.services;

import dev.prakash.productservicettsevening.Client.fakestoreapi.FakeStoreClient;
import dev.prakash.productservicettsevening.Client.fakestoreapi.FakeStoreProductDto;
import dev.prakash.productservicettsevening.dtos.ProductDto;
import dev.prakash.productservicettsevening.models.Category;
import dev.prakash.productservicettsevening.models.Product;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RequestCallback;
import org.springframework.web.client.ResponseExtractor;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.util.*;

@Service
public class FakeStoreProductServiceImpl implements ProductService {
    private RestTemplateBuilder restTemplateBuilder;
    private FakeStoreClient fakeStoreClient;
    public FakeStoreProductServiceImpl(RestTemplateBuilder restTemplateBuilder, FakeStoreClient fakeStoreClient) {
        this.restTemplateBuilder = restTemplateBuilder;
        this.fakeStoreClient = fakeStoreClient;
    }

    private Product convertFakeStoreProductDtoToProduct(FakeStoreProductDto productDto){
        Product product = new Product();
        product.setId(productDto.getId());
        product.setTitle(productDto.getTitle());
        product.setDescription(productDto.getDescription());
        product.setPrice(productDto.getPrice());
        Category category = new Category();
        category.setName(productDto.getCategory());
        product.setCategory(category);
        product.setImageUrl(productDto.getImage());
        return product;
    }
    @Override
    public Product[] getAllProducts() {
        FakeStoreProductDto[] fakeStoreProductDtos = fakeStoreClient.getAllProducts();
        Product[] answer = new Product[fakeStoreProductDtos.length];
        for(FakeStoreProductDto productDto:  fakeStoreProductDtos){
            answer[productDto.getId().intValue()-1] = convertFakeStoreProductDtoToProduct(productDto);
        }
        return answer;
    }
    /*
   * Return a Product object with all the details of the fetched product.
   * The ID of the category will be null but name of category shall be correct
   * */
    @Override
    public Optional<Product> getSingleProduct(Long productId) {
       FakeStoreProductDto productDto = fakeStoreClient.getSingleProduct(productId).orElse(null);
        if(productDto == null){
            return Optional.empty();
        }
        return Optional.of( convertFakeStoreProductDtoToProduct(productDto));
    }

    @Override
    public Product addProduct(ProductDto productDto) {
        FakeStoreProductDto fakeStoreProductDto = fakeStoreClient.addProduct(productDto);
        return convertFakeStoreProductDtoToProduct(fakeStoreProductDto);
    }

    @Override
    public Product updateProduct(Long productId, Product product) {
        FakeStoreProductDto fakeStoreProductDto = fakeStoreClient.updateProduct(productId, product);
        return convertFakeStoreProductDtoToProduct(fakeStoreProductDto);
    }

    @Override
    public Product replaceProduct(Long productId, Product product) {
       FakeStoreProductDto fakeStoreProductDto = fakeStoreClient.replaceProduct(productId, product);
        return convertFakeStoreProductDtoToProduct(fakeStoreProductDto);
    }

    @Override
    public boolean deleteProduct(Long productId) {
       Optional<FakeStoreProductDto> fakeStoreProductDtoResponseEntity=  fakeStoreClient.deleteProduct(productId);
        return fakeStoreProductDtoResponseEntity.isPresent();
    }
}
