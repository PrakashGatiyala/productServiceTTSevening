package dev.prakash.productservicettsevening.services;

import dev.prakash.productservicettsevening.Client.fakestoreapi.FakeStoreClient;
import dev.prakash.productservicettsevening.Client.fakestoreapi.FakeStoreProductDto;
import dev.prakash.productservicettsevening.dtos.ProductDto;
import dev.prakash.productservicettsevening.models.Category;
import dev.prakash.productservicettsevening.models.Product;
import dev.prakash.productservicettsevening.repositories.ProductRepository;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.data.domain.Page;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class FakeStoreProductServiceImpl implements ProductService {
    private RestTemplateBuilder restTemplateBuilder;
    private FakeStoreClient fakeStoreClient;
    private ProductRepository productRepository;
    private HashMap<Long, Object> fakeStoreProductsMap = new HashMap<>();
    private RedisTemplate<Long, Object> redisTemplate;
    public FakeStoreProductServiceImpl(RestTemplateBuilder restTemplateBuilder, FakeStoreClient fakeStoreClient, ProductRepository productRepository, RedisTemplate<Long, Object> redisTemplate) {
        this.restTemplateBuilder = restTemplateBuilder;
        this.fakeStoreClient = fakeStoreClient;
        this.productRepository = productRepository;
        this.redisTemplate=redisTemplate;
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
    public List<Product> getAllProducts() {
        FakeStoreProductDto[] fakeStoreProductDtos = fakeStoreClient.getAllProducts();
        Product[] answer = new Product[fakeStoreProductDtos.length];
        for(FakeStoreProductDto productDto:  fakeStoreProductDtos){
            answer[productDto.getId().intValue()-1] = convertFakeStoreProductDtoToProduct(productDto);
        }
        return List.of(answer);
    }
    @Override
    public Page<Product> getProducts(String query, int numberOfProducts, int offset){
        return null;
    }
    /*
   * Return a Product object with all the details of the fetched product.
   * The ID of the category will be null but name of category shall be correct
   * */
    @Override
    public Optional<Product> getSingleProduct(Long productId) {
        FakeStoreProductDto fakeStoreProductDto=
                (FakeStoreProductDto) redisTemplate.opsForHash().get(productId,"PRODUCTS");
        if(fakeStoreProductDto != null) {
            return Optional.of( convertFakeStoreProductDtoToProduct(fakeStoreProductDto) );
        }
       FakeStoreProductDto productDto = fakeStoreClient.getSingleProduct(productId).orElse(null);
//       fakeStoreProductsMap.put(productId, productDto);
        redisTemplate.opsForHash().put(productId, "PRODUCTS", productDto);
        if(productDto == null){
            return Optional.empty();
        }
        return Optional.of( convertFakeStoreProductDtoToProduct(productDto));
    }

    @Override
    public Product addProduct(ProductDto productDto) {
//        FakeStoreProductDto fakeStoreProductDto = fakeStoreClient.addProduct(productDto);
        Product product = new Product();
        product.setTitle(productDto.getTitle());
        product.setDescription(productDto.getDescription());
        product.setPrice(productDto.getPrice());
//        Category category = new Category();
//        category.setName(productDto.getCategory());
//        product.setCategory(category);
        product.setImageUrl(productDto.getImage());
        product = productRepository.save(product);

        return product;
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
