package com.example.data.corona;

import java.util.Date;
import java.util.List;

public interface CoronaDataRepository {
    Long addData(CoronaVirusDocumentDB coronaDataList);
    CoronaVirusDocumentDB showCoronaDataForSpecificDate(Date date);
    List<CoronaVirusDocumentDB> showCoronaDataFromDate1ToDate2(Date date1, Date date2);

    boolean hasDataForToday();
}
