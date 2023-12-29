package com.example.myrh.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@MappedSuperclass
public abstract class Person {

    private String first_name;
    private String last_name;
    private String email;
    private String password;
    private String image;
    private boolean isEnabled;


}
