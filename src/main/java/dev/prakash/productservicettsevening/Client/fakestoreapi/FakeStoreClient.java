package dev.prakash.productservicettsevening.Client.fakestoreapi;

import dev.prakash.productservicettsevening.dtos.ProductDto;
import dev.prakash.productservicettsevening.models.Product;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RequestCallback;
import org.springframework.web.client.ResponseExtractor;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.util.Optional;

@Component // It will tell java that there is this class in the classpath
public class FakeStoreClient {
    // Everything related to FakeStore API will be here and even Dtos will be here as well
    // We will use RestTemplate to make calls to FakeStore API
    // We will use RestTemplateBuilder to build RestTemplate
    // We will use @Value to get the base url of FakeStore API
    private RestTemplateBuilder restTemplateBuilder ;
    public FakeStoreClient(RestTemplateBuilder restTemplateBuilder) {
        this.restTemplateBuilder = restTemplateBuilder;
    }

    private  <T> ResponseEntity<T> requestForEntity(HttpMethod httpMethod, String url, @Nullable Object request, Class<T> responseType, Object... uriVariables) throws RestClientException {
        RestTemplate restTemplate = restTemplateBuilder
                .requestFactory(HttpComponentsClientHttpRequestFactory.class)
                .build();
        RequestCallback requestCallback = restTemplate.httpEntityCallback(request, responseType);
        ResponseExtractor<ResponseEntity<T>> responseExtractor = restTemplate.responseEntityExtractor(responseType);
        return (ResponseEntity)restTemplate.execute(url, httpMethod, requestCallback, responseExtractor, uriVariables);
    }
    // create getAllProduct method
    public FakeStoreProductDto convertProductToFakeStoreProductDto(Product product){
        FakeStoreProductDto fakeStoreProductDto = new FakeStoreProductDto();
        fakeStoreProductDto.setId(product.getId());
        fakeStoreProductDto.setTitle(product.getTitle());
        fakeStoreProductDto.setDescription(product.getDescription());
        fakeStoreProductDto.setPrice(product.getPrice());
        fakeStoreProductDto.setCategory(product.getCategory().getName());
        fakeStoreProductDto.setImage(product.getImageUrl());
        return fakeStoreProductDto;
    }

    public FakeStoreProductDto[] getAllProducts(){
        RestTemplate restTemplate = restTemplateBuilder.build();
        ResponseEntity<FakeStoreProductDto[]> listOfProducts = restTemplate.getForEntity("https://fakestoreapi.com/products",
                FakeStoreProductDto[].class );
        return listOfProducts.getBody();
    }

    public Optional<FakeStoreProductDto> getSingleProduct(Long productId){
        RestTemplate restTemplate = restTemplateBuilder.build();
        // {url, returnType, params_in_url}
        ResponseEntity<FakeStoreProductDto> response= restTemplate.getForEntity("https://fakestoreapi.com/products/{productId}",
                FakeStoreProductDto.class, productId);

        return Optional.ofNullable(response.getBody());
    }


   public FakeStoreProductDto addProduct( ProductDto productDto){
       RestTemplate restTemplate = restTemplateBuilder.build();
       ResponseEntity<FakeStoreProductDto> response = restTemplate.postForEntity("https://fakestoreapi.com/products",
               productDto, FakeStoreProductDto.class);
        return response.getBody();
    }
    /*
     * Product Object has only those fields filled which needs to be updated
     * Everything else is null
     * */
    public FakeStoreProductDto updateProduct( Long productId,  Product product){
        FakeStoreProductDto fakeStoreProductDto = convertProductToFakeStoreProductDto(product);

        ResponseEntity<FakeStoreProductDto> fakeStoreProductDtoResponseEntity=  requestForEntity(
                HttpMethod.PATCH,
                "https://fakestoreapi.com/products/{productId}",
                fakeStoreProductDto,
                FakeStoreProductDto.class,
                productId
        );
        return fakeStoreProductDtoResponseEntity.getBody();
    }
   public FakeStoreProductDto replaceProduct( Long productId,  Product product){
       FakeStoreProductDto fakeStoreProductDto = convertProductToFakeStoreProductDto(product);

       ResponseEntity<FakeStoreProductDto> fakeStoreProductDtoResponseEntity=  requestForEntity(
               HttpMethod.PUT,
               "https://fakestoreapi.com/products/{productId}",
               fakeStoreProductDto,
               FakeStoreProductDto.class,
               productId
       );
        return fakeStoreProductDtoResponseEntity.getBody();
    }

   public Optional<FakeStoreProductDto> deleteProduct(Long productId){
       ResponseEntity<FakeStoreProductDto> fakeStoreProductDtoResponseEntity=  requestForEntity(
               HttpMethod.DELETE,
               "https://fakestoreapi.com/products/{productId}",
               null,
               FakeStoreProductDto.class,
               productId
       );
        return Optional.ofNullable(fakeStoreProductDtoResponseEntity.getBody());
    }


}
