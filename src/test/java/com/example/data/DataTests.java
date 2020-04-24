package com.example.data;

import com.example.data.corona.*;
import org.junit.Test;

import java.time.LocalDate;
import java.util.Collections;
import java.util.Date;
import java.util.UUID;

import static org.junit.Assert.*;

public class DataTests {
    private final CoronaDataRepository coronaDataRepository = new DataRepositoryInMemory();
    private final CoronaDataAnalyzer coronaDataAnalyzer = new CoronaDataAnalyzer();
    private final CoronaDataMapper coronaDataMapper = new CoronaDataMapper(coronaDataAnalyzer);

    @Test
    public void shouldAddDataToBase(){
        CoronaVirusDocument coronaVirusDocument = CoronaVirusDocument.builder()
                .date(new Date())
                .country("Poland")
                .deathRate(00.25)
                .newConfirmed(123)
                .totalConfirmed(1234)
                .newDeaths(12)
                .totalDeaths(13)
                .newRecovered(100)
                .totalRecovered(101)
                .build();

        CoronaVirusDocumentDB coronaVirusDocumentDB =
                new CoronaVirusDocumentDB(
                        UUID.randomUUID().toString(),
                        Collections.singletonList(coronaVirusDocument),
                        null,
                        LocalDate.now());
        Long size = coronaDataRepository.addData(coronaVirusDocumentDB);

        assertEquals(1L,size.longValue());
    }
}
