package com.example.data.corona;

import lombok.RequiredArgsConstructor;
import org.apache.commons.io.input.ReversedLinesFileReader;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.Charset;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;


@RequiredArgsConstructor
public class CoronaService {
    private final CoronaDataRetriever coronaDataRetriever;
    private final CoronaDataRepository dataRepository;
    private final CoronaDataMapper mapper;

    public List<CoronaVirusData> showData() throws InterruptedException, IOException {
        CoronaData coronaData = coronaDataRetriever.getSummaryData();
        return coronaData.getCountries();

    }

    public CoronaVirusData showDataForSpecificCountry(String name) throws IOException, InterruptedException {
        return coronaDataRetriever.getSummaryData().getCountries().stream().filter(
                data -> name.equals(data.getCountrySlug())).findAny().orElse(null);
    }

    public boolean saveDataToFile(CoronaVirusData coronaVirusData, String countryName) throws IOException {
        if (countryName.equals("")) {
            return false;
        }
        File file = new File("/home/arek/Pulpit/data/" + countryName + ".txt");
        if (file.createNewFile()) {

        }

        FileWriter fileWriter = new FileWriter(file.getAbsoluteFile(), true);
        String data = LocalDate.now() + " " + coronaVirusData.toString();
        boolean checkIfAlreadyAddedDataToday;
        String lastLine;
        ReversedLinesFileReader lineReader = new ReversedLinesFileReader(file, Charset.defaultCharset());
        try {
            lastLine = lineReader.readLine();
            checkIfAlreadyAddedDataToday = lastLine.contains(coronaVirusData.getTotalConfirmed().toString());

            if (checkIfAlreadyAddedDataToday) {
                return false;
            }


        } catch (Exception e) {

            System.err.println(e);

        }
        fileWriter.append(data);
        fileWriter.flush();
        fileWriter.close();

        return true;


    }

    public boolean saveAllData(List<CoronaVirusData> dataList) {
        AtomicBoolean isDataSaved = new AtomicBoolean(false);
        dataList.forEach(c -> {
            try {
                isDataSaved.set(saveDataToFile(c, c.getCountry()));
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        return isDataSaved.get();
    }

    public CoronaVirusData getGlobalData() throws IOException, InterruptedException {
        return coronaDataRetriever.getSummaryData().getGlobalData();
    }

    public Long saveDataToDatabase(){
        try {
            if (!dataRepository.hasDataForToday())
            return dataRepository.addData(mapper.mapFromWholeJSONToEntity(coronaDataRetriever.getSummaryData()));
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }

        return 0L;

    }

    public CoronaVirusDocumentDB showDataForSpecificDay(LocalDate localDate) {
        return dataRepository.showCoronaDataForSpecificDate(
                Date.from(localDate.atStartOfDay().atZone(ZoneId.systemDefault()).toInstant()));
    }

    public List<CoronaVirusDocumentDB> showDataFromDate1ToDate2(LocalDate localDate1,LocalDate localDate2){
        Date date1 = Date.from(localDate1.atStartOfDay().atZone(ZoneId.systemDefault()).toInstant());
        Date date2 = Date.from(localDate2.atStartOfDay().atZone(ZoneId.systemDefault()).toInstant());
        return dataRepository.showCoronaDataFromDate1ToDate2(date1,date2);
    }

}
