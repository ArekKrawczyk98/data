package com.example.data;

import com.example.data.corona.CoronaData;
import com.example.data.corona.CoronaVirusData;
import org.springframework.stereotype.Service;
import org.apache.commons.io.input.ReversedLinesFileReader;

import java.io.*;
import java.nio.charset.Charset;
import java.time.LocalDate;
import java.util.List;

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
    public void saveDataToFile(CoronaVirusData coronaVirusData,String countryName) throws IOException {
        if (countryName.equals("")){
            return;
        }
        File file = new File("/home/arek/Pulpit/data/"+countryName+".txt");
     if (file.createNewFile()){

     }

     FileWriter fileWriter = new FileWriter(file.getAbsoluteFile(),true );
     String data = LocalDate.now()+" "+ coronaVirusData.toString();
     boolean checkIfAlreadyAddedDataToday = false;
     String lastLine;
     ReversedLinesFileReader lineReader = new ReversedLinesFileReader(file, Charset.defaultCharset());
     try {
         lastLine = lineReader.readLine();
         checkIfAlreadyAddedDataToday = lastLine.contains(LocalDate.now().toString());

         if (checkIfAlreadyAddedDataToday){
             return;
         }


     }catch (Exception e){

         System.out.println(e);

     }
     fileWriter.append(data);
     fileWriter.flush();
     fileWriter.close();


    }

    public void saveAllData(List<CoronaVirusData> dataList){
        dataList.forEach(c -> {
            try {
                saveDataToFile(c,c.getCountry());
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }
}
