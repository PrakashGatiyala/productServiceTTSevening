package dev.prakash.productservicettsevening.services;

import dev.prakash.productservicettsevening.dtos.ProductDto;
import dev.prakash.productservicettsevening.models.Category;
import dev.prakash.productservicettsevening.models.Product;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.List;
import java.util.Objects;

@Service
public class FakeStoreProductServiceImpl implements ProductService {
    private RestTemplateBuilder restTemplateBuilder;
    public FakeStoreProductServiceImpl(RestTemplateBuilder restTemplateBuilder) {
        this.restTemplateBuilder = restTemplateBuilder;
    }
    @Override
    public Product[] getAllProducts() {
        RestTemplate restTemplate = restTemplateBuilder.build();
        ResponseEntity<ProductDto[]> listOfProducts = restTemplate.getForEntity("https://fakestoreapi.com/products",
                 ProductDto[].class );
        Product[] answer = new Product[listOfProducts.getBody().length];
        for(ProductDto productDto: listOfProducts.getBody()){
            Product product = new Product();
            product.setId(productDto.getId());
            product.setTitle(productDto.getTitle());
            product.setDescription(productDto.getDescription());
            product.setPrice(productDto.getPrice());
            Category category = new Category();
            category.setName(productDto.getCategory());
            product.setCategory(category);
            product.setImageUrl(productDto.getImage());
            answer[productDto.getId().intValue()-1] = product;
        }
        return answer;
//        System.out.println(listOfProducts.getBody());
//        Product[] arr = new Product[ listOfProducts.getBody().size()];
//        for(int i=0; i< listOfProducts.getBody().size(); i++){
//            arr[i] = new Product();
//            arr[i].setId(((Integer)((HashMap)listOfProducts.getBody().get(i)).get("id")).longValue());
//            arr[i].setTitle((String)((HashMap)listOfProducts.getBody().get(i)).get("title"));
//            arr[i].setDescription((String)((HashMap)listOfProducts.getBody().get(i)).get("description"));
//            // arr[i].setPrice((Double)((HashMap)listOfProducts.getBody().get(i)).get("price"));
//            arr[i].setPrice(((Number)((HashMap<?, ?>) listOfProducts.getBody().get(i)).get("price")).doubleValue());
//
//            arr[i].setImageUrl((String)((HashMap)listOfProducts.getBody().get(i)).get("image"));
//            // arr[i].setCategory((String)((HashMap)listOfProducts.getBody().get(i)).get("category"));
//
//        }
//        return arr;
    }
   /*
   * Return a Product object with all the details of the fetched product.
   * The ID of the category will be null but name of category shall be correct
   * */
    @Override
    public Product getSingleProduct(Long productId) {
        RestTemplate restTemplate = restTemplateBuilder.build();
        // {url, returnType, params_in_url}
        ResponseEntity<ProductDto> response= restTemplate.getForEntity("https://fakestoreapi.com/products/{productId}",
                ProductDto.class, productId);
//        if(response.getStatusCode().is2xxSuccessful())
//            return response.getBody().toProduct();
//        else
//            return null;
        ProductDto productDto = response.getBody();
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
    public Product addProduct(ProductDto productDto) {


        RestTemplate restTemplate = restTemplateBuilder.build();
        ResponseEntity<ProductDto> response = restTemplate.postForEntity("https://fakestoreapi.com/products",
                productDto, ProductDto.class);
        ProductDto productDto1 = response.getBody();
        Product product1 = new Product();
        product1.setId(productDto1.getId());
        product1.setTitle(productDto1.getTitle());
        product1.setDescription(productDto1.getDescription());
        product1.setPrice(productDto1.getPrice());
        Category category = new Category();
        // System.out.println(category.getId());
        category.setName(productDto1.getCategory());
        product1.setCategory(category);
        product1.setImageUrl(productDto1.getImage());
        return product1;
    }

    @Override
    public Product updateProduct(Long productId, Product product) {
        return null;
    }

    @Override
    public boolean deleteProduct(Long productId) {
        return false;
    }
}
