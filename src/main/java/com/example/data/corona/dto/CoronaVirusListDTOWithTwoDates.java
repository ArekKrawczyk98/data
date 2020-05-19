package com.example.data.corona.dto;

import com.example.data.corona.CoronaVirusData;
import lombok.Value;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

@Value
public class CoronaVirusListDTOWithTwoDates implements Serializable {
    List<CoronaVirusData> coronaVirusData;
    LocalDate firstDate;
    LocalDate secondDate;
}
