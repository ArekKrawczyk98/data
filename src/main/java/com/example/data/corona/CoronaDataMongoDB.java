package com.example.data.corona;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.time.LocalDate;
import java.util.List;

public interface CoronaDataMongoDB extends MongoRepository<CoronaVirusDocumentDB, String> {
    CoronaVirusDocumentDB findByDate(LocalDate date);
    @Query("{ 'date' : { $gte : ?0, $lte: ?1 } }")
    List<CoronaVirusDocumentDB> findAllByDateBetween(LocalDate date1, LocalDate date2);
}
