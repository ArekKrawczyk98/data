package com.example.data;

import com.example.data.corona.CoronaData;
import com.example.data.corona.CoronaVirusData;
import org.springframework.stereotype.Service;
import org.apache.commons.io.input.ReversedLinesFileReader;

import java.io.*;
import java.nio.charset.Charset;
import java.time.LocalDate;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;

@Service
public class CoronaService {
    CoronaDataRetriever coronaDataRetriever = new CoronaDataRetriever();
    public List<CoronaVirusData> showData() throws InterruptedException, IOException {
        CoronaData coronaData = coronaDataRetriever.getSummaryData();
        return coronaData.getCountries();

    }

    public CoronaVirusData showDataForSpecificCountry(String name) throws IOException, InterruptedException {
        return  coronaDataRetriever.getSummaryData().getCountries().stream().filter(
                data -> name.equals(data.getCountrySlug())).findAny().orElse(null);
    }
    public boolean saveDataToFile(CoronaVirusData coronaVirusData,String countryName) throws IOException {
        if (countryName.equals("")){
            return false;
        }
        File file = new File("/home/arek/Pulpit/data/"+countryName+".txt");
     if (file.createNewFile()){

     }

     FileWriter fileWriter = new FileWriter(file.getAbsoluteFile(),true );
     String data = LocalDate.now()+" "+ coronaVirusData.toString();
     boolean checkIfAlreadyAddedDataToday;
     String lastLine;
     ReversedLinesFileReader lineReader = new ReversedLinesFileReader(file, Charset.defaultCharset());
     try {
         lastLine = lineReader.readLine();
         checkIfAlreadyAddedDataToday = lastLine.contains(coronaVirusData.getTotalConfirmed().toString());

         if (checkIfAlreadyAddedDataToday){
             return false;
         }


     }catch (Exception e){

         System.out.println(e);

     }
     fileWriter.append(data);
     fileWriter.flush();
     fileWriter.close();

     return true;


    }

    public boolean saveAllData(List<CoronaVirusData> dataList){
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
}
