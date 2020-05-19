package com.example.data.corona;

import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@AllArgsConstructor
public class CoronaConfiguration {
    @Bean
    CoronaService coronaService(CoronaDataRetriever coronaDataRetriever,
                                CoronaDataRepository coronaDataRepository,
                                CoronaDataMapper coronaDataMapper){
        return new CoronaService(coronaDataRetriever,coronaDataRepository,coronaDataMapper);
    }
    @Bean
    CoronaDataAnalyzer coronaDataAnalyzer(){
        return new CoronaDataAnalyzer();
    }
    @Bean
    CoronaDataRetriever coronaDataRetriever(){
        return new CoronaDataRetriever();
    }
    @Bean
    CoronaDataRepository coronaDataRepository(CoronaDataMongoDB coronaDataMongoDB){
        return new CoronaDataRepositoryImpl(coronaDataMongoDB);
    }
    @Bean
    CoronaDataMapper coronaDataMapper(){
        return new CoronaDataMapper(new CoronaDataAnalyzer());
    }

}
