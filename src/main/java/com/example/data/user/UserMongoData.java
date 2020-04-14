package com.example.data.user;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface UserMongoData extends MongoRepository <User,Integer> {
    User findByUsername(String username);
}
