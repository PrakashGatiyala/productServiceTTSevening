package dev.prakash.productservicettsevening.dtos;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GetProductsRequestDto {
    private String query;
    private int numberOfResults;
    private int offset;
}
