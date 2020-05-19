package com.example.data.corona;

import lombok.Value;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import static java.time.temporal.ChronoUnit.DAYS;

@Value
public class CoronaDataAnalyzer {

    public BigDecimal virusDeathRate(CoronaVirusData data) {
        return BigDecimal.valueOf(((double) data.getNewDeaths() / data.getTotalConfirmed()) * 100.00);
    }
    public BigDecimal averageConfirmedGrowthPerDay(LocalDate localDate1,LocalDate localDate2,List<CoronaVirusData> data){
        double days = DAYS.between(localDate1,localDate2);
        return BigDecimal.valueOf(data.stream().map(CoronaVirusData::getTotalConfirmed).count()/days);
    }

}
