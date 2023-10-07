package dev.prakash.productservicettsevening.inheritanceexample.mappedsuperclass;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@MappedSuperclass
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY )
    private long id;
    private String email;
    private String password;
}
