package com.example.demo.entity;


import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

@Getter
@Setter
@Accessors(chain = true)
public class User {
    private String userName;
    private String passWord;
    private String name;
    private int age;
    private String email;

    public User() {
    }



}
