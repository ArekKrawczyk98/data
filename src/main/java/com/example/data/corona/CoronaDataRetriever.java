package com.example.data.corona;


import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Value;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;


@Value
public class CoronaDataRetriever {
    HttpClient httpClient = HttpClient.newBuilder().build();
    ObjectMapper objectMapper = new ObjectMapper();

    public CoronaData getSummaryData() throws IOException, InterruptedException {
        HttpRequest httpRequest = HttpRequest.newBuilder()
                .uri(URI.create("https://api.covid19api.com/summary"))
                .GET()
                .build();

        HttpResponse<String> httpResponse =
                httpClient.send(httpRequest, HttpResponse.BodyHandlers.ofString());


        return objectMapper.readValue(httpResponse.body(), CoronaData.class);


    }
}
