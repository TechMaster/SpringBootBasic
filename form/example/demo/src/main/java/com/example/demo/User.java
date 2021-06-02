package com.example.demo;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class User {
    private String firstName;
    private String lastName;
    private String nationality;
    private boolean isSingle = true;
    public User(String firstName, String lastName, String nationality) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.nationality = nationality;
    }
    
}
