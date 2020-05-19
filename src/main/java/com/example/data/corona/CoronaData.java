package com.example.data.corona;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;
import java.util.List;


@NoArgsConstructor
@AllArgsConstructor
@Getter
public class CoronaData implements Serializable {

    @JsonProperty("Global")
    CoronaVirusData globalData;
    @JsonProperty("Countries")
    List<CoronaVirusData> coronaVirusData = null;

    @JsonProperty("Date")
    Date date;

    public List<CoronaVirusData> getCountries() {
        return coronaVirusData;
    }
}
