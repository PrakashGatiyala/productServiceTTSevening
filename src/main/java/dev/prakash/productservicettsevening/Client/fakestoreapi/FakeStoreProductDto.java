package dev.prakash.productservicettsevening.Client.fakestoreapi;

import dev.prakash.productservicettsevening.dtos.RatingDto;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class FakeStoreProductDto {
    private Long id;
    private String title;

    private String description;
    private double price;
    private String image;
    private String category;
    private RatingDto rating;



}
