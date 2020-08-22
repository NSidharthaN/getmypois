package com.daimlerpoi.getmypois.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class HereClient {

    private final RestTemplate restTemplate = new RestTemplate();

    @Value("${herePlacesBaseUri}")
    private String herePlacesBaseUri;

    @PostConstruct
    public void invokeHereApi() {


        System.out.println("Hey there \n \n");


        long start = System.nanoTime();
        ExecutorService executor = Executors.newFixedThreadPool(3);
        List<CompletableFuture<String>> futures =
                Stream.of("restaurant", "parking-facility", "ev-charging-station")
                        .map(t -> CompletableFuture.supplyAsync(() -> invokeHereApi(t), executor))
                        .collect(Collectors.toList());

        String result =
                futures.stream()
                        .map(CompletableFuture::join)
                        .collect(Collectors.joining(","));
        long duration = (System.nanoTime() - start) / 1_000_000;
        System.out.printf("Processed tasks in %d millis\n", duration);
        System.out.println("Processed Response Result below ********* \n\n *****" + "[" + result + "]");
        executor.shutdown();

    }

    public String invokeHereApi(String category) {

        UriComponentsBuilder uriComponentsBuilder = UriComponentsBuilder.fromUriString(herePlacesBaseUri + "/v1/browse")
                // Add query parameter
                .queryParam("at", "48.8465,2.3722")
                .queryParam("cat", category)
                .queryParam("app_code", "FzyuK6KjQQGwZ88OW2umBg")
                .queryParam("app_id", "c0zGYugKrHb8Vppa8L31")
                .queryParam("size", "3");


        ResponseEntity<String> responseEntity = restTemplate.getForEntity(uriComponentsBuilder.build().toUri(), String.class);

        return responseEntity.getBody();
    }


}
