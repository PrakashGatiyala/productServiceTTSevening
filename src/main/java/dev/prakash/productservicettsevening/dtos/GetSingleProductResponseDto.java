package dev.prakash.productservicettsevening.dtos;

import dev.prakash.productservicettsevening.models.Product;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GetSingleProductResponseDto {
    private Product product;
}
