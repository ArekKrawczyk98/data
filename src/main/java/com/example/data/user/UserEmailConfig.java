package com.example.data.user;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;


@Getter
public class UserEmailConfig {
    @Value("${spring.mail.host}")
    private String host;
    @Value("${spring.mail.port}")
    private int port;
    @Value("${spring.mail.username}")
    private String username;
    @Value("${spring.mail.password}")
    private String password;

}
