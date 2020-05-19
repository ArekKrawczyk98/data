package com.example.data;

import com.example.data.corona.CoronaDataRepository;
import com.example.data.corona.CoronaVirusDocumentDB;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

public class DataRepositoryInMemory implements CoronaDataRepository {
    private final HashMap<String, CoronaVirusDocumentDB> database = new HashMap<>();

    public boolean date1IsEqualOrGreaterThanDate2(LocalDate date1, LocalDate date2){
       int x = date1.compareTo(date2);

        return x >= 0;
    }
    public boolean date1IsEqualOrLesserThanDate2(LocalDate date1, LocalDate date2){
        int x = date1.compareTo(date2);

        return x <= 0;
    }

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
        LocalDate localDate1 = date1.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        LocalDate localDate2 = date2.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();

       return database.values().stream()
                .filter(x ->date1IsEqualOrGreaterThanDate2(x.getDate(),localDate1)
                && date1IsEqualOrLesserThanDate2(x.getDate(),localDate2)).collect(Collectors.toList());

    }

    @Override
    public boolean hasDataForToday() {
        return database.values().stream().anyMatch(x ->x.getDate().isEqual(LocalDate.now()));
    }
}
