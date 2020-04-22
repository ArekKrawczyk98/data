package com.example.data.user;

import com.example.data.security.UserSecurityService;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@AllArgsConstructor
public class UserConfig {
    @Bean
    UserRepository userRepository(UserMongoData userMongoData){
        return new UserRepositoryMongoDB(userMongoData);
    }
    @Bean
    UserService userService(UserRepository userRepository){
        return new UserService(userRepository);
    }
    @Bean
    UserSecurityService userSecurityService(UserRepository userRepository){
        return new UserSecurityService(userRepository);
    }
}
