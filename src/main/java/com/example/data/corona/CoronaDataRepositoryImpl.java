package com.example.data.corona;

import lombok.Value;
import org.springframework.data.domain.Example;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;

@Value
public class CoronaDataRepositoryImpl implements CoronaDataRepository {

    CoronaDataMongoDB coronaDataMongoDB;
    @Override
    public Long addData(CoronaVirusDocumentDB coronaDataList) {
        long count = coronaDataMongoDB.count();
        coronaDataMongoDB.save(coronaDataList);
        return count - coronaDataMongoDB.count();
    }



    @Override
    public CoronaVirusDocumentDB showCoronaDataForSpecificDate(Date date) {
        LocalDate localDate = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        return coronaDataMongoDB.findByDate(localDate);
    }

    @Override
    public List<CoronaVirusDocumentDB> showCoronaDataFromDate1ToDate2(Date date1, Date date2) {
        LocalDate localDate1 = date1.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        LocalDate localDate2 = date2.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        return coronaDataMongoDB.findAllByDateBetween(localDate1,localDate2);
    }

    @Override
    public boolean hasDataForToday() {
        CoronaVirusDocumentDB coronaVirusDocumentDB = coronaDataMongoDB.findByDate(LocalDate.now());
        return coronaDataMongoDB.exists(Example.of(coronaVirusDocumentDB));
    }
}
