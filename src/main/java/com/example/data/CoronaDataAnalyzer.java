package com.example.data;

import com.example.data.corona.CoronaVirusData;
import lombok.Value;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Value
@Service
public class CoronaDataAnalyzer {

    public BigDecimal virusDeathRate(CoronaVirusData data){
        return  BigDecimal.valueOf(((double)data.getNewDeaths() / data.getTotalConfirmed())*100.00);
    }
}
