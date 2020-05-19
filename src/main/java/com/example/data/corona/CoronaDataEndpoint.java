package com.example.data.corona;

import com.example.data.corona.dto.CoronaVirusListDTOWithTwoDates;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("data")
public class CoronaDataEndpoint {

    final
    CoronaService coronaService;
    final
    CoronaDataAnalyzer coronaDataAnalyzer;

    public CoronaDataEndpoint(CoronaService coronaService, CoronaDataAnalyzer coronaDataAnalyzer) {
        this.coronaService = coronaService;
        this.coronaDataAnalyzer = coronaDataAnalyzer;
    }

    @GetMapping
    public List<CoronaVirusData> defaultVirusData() throws InterruptedException, IOException {

        return coronaService.showData();

    }

    @GetMapping("/{name}")
    public CoronaVirusData countryData(@PathVariable("name") String name) throws InterruptedException, IOException {

        String realName = name.toLowerCase();
        return coronaService.showDataForSpecificCountry(realName);

    }

    @GetMapping("/saveLocally")
    public String saveAllData() throws IOException, InterruptedException {
        boolean isSavingSuccessful = coronaService.saveAllData(defaultVirusData());
        if (isSavingSuccessful) {
            return "Data saved!";
        } else {
            return "Data didnt save";
        }
    }

    @GetMapping("/{country}/deathrate")
    public String deathRate(@PathVariable("country") String country){
        String realName = country.toLowerCase();
        try {
            CoronaVirusData virusData = coronaService.showDataForSpecificCountry(realName);
            return coronaDataAnalyzer
                    .virusDeathRate(virusData)
                    .toString()
                    + "%";
        } catch (Exception e) {
            return "Country doesnt exist in database";
        }


    }

    @GetMapping("/global")
    public String globalData() throws IOException, InterruptedException {
        CoronaVirusData data = coronaService.getGlobalData();

        return "Corona virus data for " + LocalDate.now()
                + "\nNew confirmed:" + data.getNewConfirmed()
                + "\nTotal confirmed:" + data.getTotalConfirmed()
                + "\nNew deaths:" + data.getNewDeaths()
                + "\nTotal deaths:" + data.getTotalDeaths()
                + "\nNew recovered:" + data.getNewRecovered()
                + "\nTotal recovered:" + data.getTotalRecovered()
                + "\nDeath rate:" + coronaDataAnalyzer.virusDeathRate(data).
                setScale(4, RoundingMode.HALF_UP).doubleValue() + "%";
    }

    @GetMapping("/saveToDatabase")
    public Long saveToDatabase(){
        return coronaService.saveDataToDatabase();
    }

    @GetMapping("{month}/{day}")
    public CoronaVirusDocumentDB getDocumentForSpecificDay(@PathVariable("month") String month,@PathVariable("day")String day){
        LocalDate localDate = LocalDate.of(2020,Integer.parseInt(month),Integer.parseInt(day));
       return coronaService.showDataForSpecificDay(localDate);
    }
    @GetMapping("{month1}/{day1}/{month2}/{day2}")
    public List<CoronaVirusDocumentDB> getDocumentsFromDate1ToDate2(@PathVariable("month1")String month1,
                                                                    @PathVariable("month2")String month2,
                                                                    @PathVariable("day1")String day1,
                                                                    @PathVariable("day2")String day2){

        LocalDate localDate1 = LocalDate.of(2020,Integer.parseInt(month1),Integer.parseInt(day1));
        LocalDate localDate2 = LocalDate.of(2020,Integer.parseInt(month2),Integer.parseInt(day2));
        return coronaService.showDataFromDate1ToDate2(localDate1,localDate2);

    }

    @GetMapping("/analyzeGrowthPerDay")
    public BigDecimal getGrowthPerDay(@RequestBody CoronaVirusListDTOWithTwoDates coronaVirusDTOWithTwoDates){

        return coronaDataAnalyzer.averageConfirmedGrowthPerDay(coronaVirusDTOWithTwoDates.getFirstDate(),
                coronaVirusDTOWithTwoDates.getSecondDate(),
                coronaVirusDTOWithTwoDates.getCoronaVirusData());


    }

}
