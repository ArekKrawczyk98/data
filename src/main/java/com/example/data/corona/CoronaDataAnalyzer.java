package com.example.data.corona;

import lombok.Value;

import java.math.BigDecimal;
import java.util.List;

@Value
public class CoronaDataAnalyzer {

    public BigDecimal virusDeathRate(CoronaVirusData data) {
        return BigDecimal.valueOf(((double) data.getNewDeaths() / data.getTotalConfirmed()) * 100.00);
    }
    public BigDecimal averageConfirmedGrowthPerDay(List<CoronaVirusData> data){
        return BigDecimal.valueOf(data.stream().map(CoronaVirusData::getTotalConfirmed).count()/data.size());

    }
}
