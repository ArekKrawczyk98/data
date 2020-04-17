package com.example.data.user;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.Optional;

@RepositoryRestResource
public interface UserMongoData extends MongoRepository <User,Integer> {
    Optional<User> findByUsername(String username);
}
