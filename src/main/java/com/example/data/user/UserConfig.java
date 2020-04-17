package com.example.data.user;

import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@AllArgsConstructor
public class UserConfig {
    @Bean
    UserRepository userRepository(UserMongoData userMongoData){
        return new UserDAO(userMongoData);
    }
    @Bean
    UserService userService(UserRepository userRepository){
        return new UserService(userRepository);
    }
}
