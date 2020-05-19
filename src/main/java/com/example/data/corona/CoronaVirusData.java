package com.example.data.corona;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.io.Serializable;


@Getter
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties({"Date", "CountryCode"})
public class CoronaVirusData implements Serializable {
    @JsonProperty("Country")
    private String country;
    @JsonProperty("Slug")
    private String countrySlug;
    @JsonProperty("NewConfirmed")
    private Integer newConfirmed;
    @JsonProperty("TotalConfirmed")
    private Integer totalConfirmed;
    @JsonProperty("NewDeaths")
    private Integer newDeaths;
    @JsonProperty("TotalDeaths")
    private Integer totalDeaths;
    @JsonProperty("NewRecovered")
    private Integer newRecovered;
    @JsonProperty("TotalRecovered")
    private Integer totalRecovered;


    @Override
    public String toString() {
        return "VirusData" +
                "country='" + country + '\'' +
                ", countrySlug='" + countrySlug + '\'' +
                ", newConfirmed=" + newConfirmed +
                ", totalConfirmed=" + totalConfirmed +
                ", newDeaths=" + newDeaths +
                ", totalDeaths=" + totalDeaths +
                ", newRecovered=" + newRecovered +
                ", totalRecovered=" + totalRecovered +
                '\n';
    }
}
