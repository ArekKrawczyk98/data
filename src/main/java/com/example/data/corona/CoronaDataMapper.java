package com.example.data.corona;

import lombok.Value;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Value
public class CoronaDataMapper {
    CoronaDataAnalyzer coronaDataAnalyzer;
    public CoronaVirusDocumentDB mapFromWholeJSONToEntity(CoronaData data){
        List<CoronaVirusDocument> list = data.getCoronaVirusData()
                .stream()
                .map(coronaVirusData -> mapFromJSONToDocument(coronaVirusData,0.0))
                .collect(Collectors.toList());
        return new CoronaVirusDocumentDB(UUID.randomUUID().toString(),
                list,
                mapFromJSONToDocument(data.getGlobalData(),coronaDataAnalyzer.virusDeathRate(data.getGlobalData()).doubleValue()),
                data.getDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate());
    }
    public CoronaVirusDocument mapFromJSONToDocument(CoronaVirusData coronaVirusData,Double deathRate){
        return CoronaVirusDocument.builder()
                .country(coronaVirusData.getCountry())
                .date(Date.from(LocalDate.now().atStartOfDay().atZone(ZoneId.systemDefault()).toInstant()))
                .deathRate(deathRate)
                .newConfirmed(coronaVirusData.getNewConfirmed())
                .newDeaths(coronaVirusData.getNewDeaths())
                .newRecovered(coronaVirusData.getNewRecovered())
                .totalConfirmed(coronaVirusData.getTotalConfirmed())
                .totalDeaths(coronaVirusData.getTotalDeaths())
                .totalRecovered(coronaVirusData.getTotalRecovered())
                .build();
    }
}
