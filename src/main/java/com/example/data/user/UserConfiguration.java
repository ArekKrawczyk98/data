package com.example.data.user;

import com.example.data.security.UserSecurityService;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@AllArgsConstructor
public class UserConfiguration {
    @Bean
    UserRepository userRepository(UserMongoData userMongoData){
        return new UserRepositoryMongoDB(userMongoData);
    }
    @Bean
    UserService userService(UserRepository userRepository,UserEmailService userEmailService){
        return new UserService(userRepository,userEmailService);
    }
    @Bean
    UserSecurityService userSecurityService(UserRepository userRepository){
        return new UserSecurityService(userRepository);
    }
    @Bean
    UserEmailConfig userEmailConfig(){
        return new UserEmailConfig();
    }
    @Bean
    UserEmailService userEmailService(){
        return UserEmailService.createService();
    }
}
