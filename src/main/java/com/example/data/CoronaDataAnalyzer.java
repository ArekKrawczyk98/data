package com.example.data;

import com.example.data.corona.CoronaVirusData;
import lombok.Value;
import org.springframework.stereotype.Service;

@Value
@Service
public class CoronaDataAnalyzer {

    public Double virusGrowthPercentage(CoronaVirusData dataFromYesterday, CoronaVirusData dataFromToday){
        return (double) (dataFromToday.getNewConfirmed() / dataFromYesterday.getTotalConfirmed() * 100.00);
    }
    public Double virusDeathRate(CoronaVirusData data){
        return  ((double)data.getNewDeaths() / data.getTotalConfirmed())*100.00;
    }
}
