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
}
