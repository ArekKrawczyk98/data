package com.example.data.corona;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Date;


@Getter
@NoArgsConstructor
@AllArgsConstructor
public class CoronaVirusDocument {
    private Date date;
    private String country;
    private Double deathRate;
    private Integer newConfirmed;
    private Integer totalConfirmed;
    private Integer newDeaths;
    private Integer totalDeaths;
    private Integer newRecovered;
    private Integer totalRecovered;

    public static Builder builder(){
        return new Builder();
    }

    public static final class Builder{
        private Date date;
        private String country;
        private Double deathRate;
        private Integer newConfirmed;
        private Integer totalConfirmed;
        private Integer newDeaths;
        private Integer totalDeaths;
        private Integer newRecovered;
        private Integer totalRecovered;




        public Builder date(Date date){
            this.date = date;
            return this;
        }
        public Builder country(String country){
            this.country = country;
            return this;
        }
        public Builder deathRate(Double deathRate){
            this.deathRate = deathRate;
            return this;
        }
        public Builder newConfirmed(Integer newConfirmed){
            this.newConfirmed = newConfirmed;
            return this;
        }
        public Builder totalConfirmed(Integer totalConfirmed){
            this.totalConfirmed = totalConfirmed;
            return this;
        }
        public Builder newDeaths(Integer newDeaths){
            this.newDeaths = newDeaths;
            return this;
        }
        public Builder totalDeaths(Integer totalDeaths){
            this.totalDeaths = totalDeaths;
            return this;
        }
        public Builder newRecovered(Integer newRecovered){
            this.newRecovered = newRecovered;
            return this;
        }
        public Builder totalRecovered(Integer totalRecovered){
            this.totalRecovered = totalRecovered;
            return this;
        }
        public CoronaVirusDocument build(){
            CoronaVirusDocument document  = new CoronaVirusDocument();
            document.date = this.date;
            document.country = this.country;
            document.deathRate = this.deathRate;
            document.newConfirmed = this.newConfirmed;
            document.totalConfirmed = this.totalConfirmed;
            document.newDeaths = this.newDeaths;
            document.totalDeaths = this.totalDeaths;
            document.newRecovered = this.newRecovered;
            document.totalRecovered = this.totalRecovered;
            return document;

        }

    }

}
