package dev.prakash.productservicettsevening.Client.authenticationclient;

import dev.prakash.productservicettsevening.Client.authenticationclient.Dtos.ValidateTokenRequestDto;
import dev.prakash.productservicettsevening.Client.authenticationclient.Dtos.ValidateTokenResponseDto;
import dev.prakash.productservicettsevening.Client.fakestoreapi.FakeStoreProductDto;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class AuthenticationClient {
    private RestTemplateBuilder restTemplateBuilder ;
    public AuthenticationClient(RestTemplateBuilder restTemplateBuilder) {
        this.restTemplateBuilder = restTemplateBuilder;
    }
    public ValidateTokenResponseDto validate(String token, Long userID){
        ValidateTokenRequestDto requestDto = new ValidateTokenRequestDto();
        requestDto.setToken(token);
        requestDto.setUserId(userID);
        RestTemplate restTemplate = restTemplateBuilder.build();
        ResponseEntity<ValidateTokenResponseDto> listOfProducts = restTemplate.postForEntity(
                "http://localhost:9000/auth/validate",
               requestDto,
               ValidateTokenResponseDto.class );
        return  listOfProducts.getBody();
    }
//    public void getUserDetails(String token){
//
//    }
}
