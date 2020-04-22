package com.example.data.corona;

import lombok.Value;

import java.math.BigDecimal;

@Value
public class CoronaDataAnalyzer {

    public BigDecimal virusDeathRate(CoronaVirusData data) {
        return BigDecimal.valueOf(((double) data.getNewDeaths() / data.getTotalConfirmed()) * 100.00);
    }
}
