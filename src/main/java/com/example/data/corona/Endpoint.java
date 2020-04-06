package com.example.data.corona;

import com.example.data.CoronaDataAnalyzer;
import com.example.data.CoronaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.util.List;

@RestController
public class Endpoint {

    @Autowired
    CoronaService coronaService;
    @Autowired
    CoronaDataAnalyzer coronaDataAnalyzer;

    @GetMapping
    public List<CoronaVirusData> defaultVirusData() throws InterruptedException, IOException {

        return coronaService.showData();

    }

    @GetMapping("/{name}")
    public CoronaVirusData countryData(@PathVariable("name") String name) throws InterruptedException, IOException {

        String realName = name.toLowerCase();
        return coronaService.showDataForSpecificCountry(realName);

    }

    @GetMapping("/save")
    public String saveAllData() throws IOException, InterruptedException {
        boolean isSavingSuccessful =  coronaService.saveAllData(defaultVirusData());
        if (isSavingSuccessful){
            return "Data saved!";
        }
        else {
            return "Data didnt save";
        }
    }
    @GetMapping("/{country}/deathrate")
    public String deathRate(@PathVariable("country") String country) throws IOException, InterruptedException {
        String realName = country.toLowerCase();
        CoronaVirusData virusData = coronaService.showDataForSpecificCountry(realName);
        return coronaDataAnalyzer
                .virusDeathRate(virusData)
                .toString()
                +"%";


    }
    @GetMapping("/global")
    public String globalData() throws IOException, InterruptedException {
        CoronaVirusData data = coronaService.getGlobalData();

        return "Corona virus data for " + LocalDate.now()
                + "\nNew confirmed:"+data.getNewConfirmed()
                + "\nTotal confirmed:" + data.getTotalConfirmed()
                + "\nNew deaths:" + data.getNewDeaths()
                + "\nTotal deaths:" + data.getTotalDeaths()
                + "\nNew recovered:"+ data.getNewRecovered()
                + "\nTotal recovered:" + data.getTotalRecovered()
                + "\nDeath rate:"+coronaDataAnalyzer.virusDeathRate(data).setScale(4, RoundingMode.HALF_UP).doubleValue()+"%";
    }

}
