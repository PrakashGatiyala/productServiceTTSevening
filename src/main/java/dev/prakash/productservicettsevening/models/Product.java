package dev.prakash.productservicettsevening.models;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class Product extends BaseModel {
    private String title;
    private String description;
    private double price;
    private Category category;
    private String imageUrl;
}
