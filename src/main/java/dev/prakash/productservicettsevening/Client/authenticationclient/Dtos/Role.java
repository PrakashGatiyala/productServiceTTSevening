package dev.prakash.productservicettsevening.Client.authenticationclient.Dtos;

import dev.prakash.productservicettsevening.models.BaseModel;

import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class Role extends BaseModel {
    private String name;
//    private List<User> users
}