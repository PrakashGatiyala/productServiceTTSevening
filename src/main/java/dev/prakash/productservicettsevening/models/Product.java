package dev.prakash.productservicettsevening.models;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@Entity
public class Product extends BaseModel {
    private String title;
    private String description;
    private double price;
    // P: C -> M:1 (Many to One)
    @ManyToOne
    private Category category;
    private String imageUrl;
}
