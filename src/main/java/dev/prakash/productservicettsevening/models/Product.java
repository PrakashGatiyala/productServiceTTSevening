package dev.prakash.productservicettsevening.models;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

@Getter
@Setter
@ToString
@Entity
public class Product extends BaseModel implements Serializable {
    private String title;
    private String description;
    private double price;
    // P: C -> M:1 (Many to One)
    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.REMOVE })
    private Category category;
    private String imageUrl;
    private boolean isPublic;
}
