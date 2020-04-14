package com.example.data.corona;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Date;
import java.util.List;


@Setter
@Getter
public class CoronaData implements Serializable {

    @JsonProperty("Global")
    CoronaVirusData globalData;
    @JsonProperty("Countries")
    List<CoronaVirusData> coronaVirusData = null;

    @JsonProperty("Date")
    Date date;

    CoronaData() {
    }

    public List<CoronaVirusData> getCountries() {
        return coronaVirusData;
    }
}
