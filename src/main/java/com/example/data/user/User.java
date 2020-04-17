package com.example.data.user;

import lombok.Value;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Value
@Document(collection = "users")
public class User {
    @Id
    String id;
    String username;
    String password;
    List<String> countriesTracked;

    public User changeName(String name){
        return new User(id,name,password,countriesTracked);
    }
}
