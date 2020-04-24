package com.example.data.corona;

import lombok.Value;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;
import java.util.List;

@Document("data")
@Value
public class CoronaVirusDocumentDB {
    @Id
    String id;
    List<CoronaVirusDocument> documents;
    CoronaVirusDocument global;
    LocalDate date;
}
