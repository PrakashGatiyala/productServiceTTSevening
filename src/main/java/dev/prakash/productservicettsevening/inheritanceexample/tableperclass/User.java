package dev.prakash.productservicettsevening.inheritanceexample.tableperclass;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity(name = "tbc_user")
@Inheritance(strategy = jakarta.persistence.InheritanceType.TABLE_PER_CLASS)
public class User {
    @Id
    @GeneratedValue(strategy = jakarta.persistence.GenerationType.SEQUENCE)
    private long id;
    private String email;
    private String password;
}
