package com.example.data.corona;

import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@AllArgsConstructor
public class CoronaConfig {
    @Bean
    CoronaService coronaService(){
        return new CoronaService();
    }
    @Bean
    CoronaDataAnalyzer coronaDataAnalyzer(){
        return new CoronaDataAnalyzer();
    }
}
