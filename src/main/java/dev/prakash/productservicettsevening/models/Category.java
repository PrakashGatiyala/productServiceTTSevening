package dev.prakash.productservicettsevening.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.BatchSize;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import java.io.Serializable;
import java.util.List;
@Getter
@Setter
@Entity
public class Category extends BaseModel implements Serializable {
    private String name;
    private String description;
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "category", cascade = CascadeType.ALL)
    @Fetch(value = FetchMode.JOIN)
    @JsonIgnore
    private List<Product> products;

}
// FetchMode allow to solve how a associated object fetched
// Spring Data JPA modifies the behaviour of FetchMode a lot
// FetchMode Type Select -> It will execute each query separately
// FetchMode Type Join -> It will execute a single query with join
// FetchMode Type SubSelect -> It will execute a single query with sub-query

