package com.example.data;

import com.example.data.corona.CoronaDataRepository;
import com.example.data.corona.CoronaVirusDocumentDB;

import java.time.ZoneId;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

public class DataRepositoryInMemory implements CoronaDataRepository {
    private final HashMap<String, CoronaVirusDocumentDB> database = new HashMap<>();

    @Override
    public Long addData(CoronaVirusDocumentDB coronaDataList) {
         database.put(String.valueOf(database.size()+1),coronaDataList);
         return (long) database.size();
    }


    @Override
    public CoronaVirusDocumentDB showCoronaDataForSpecificDate(Date date) {
       return database.values().stream()
               .filter(c -> c.getDate().equals(
                       date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate())).findAny().orElseThrow();
    }

    @Override
    public List<CoronaVirusDocumentDB> showCoronaDataFromDate1ToDate2(Date date1, Date date2) {
        return null;
    }

    @Override
    public boolean hasDataForToday() {
        return false;
    }
}
