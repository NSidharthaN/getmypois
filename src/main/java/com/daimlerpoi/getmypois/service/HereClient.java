package com.daimlerpoi.getmypois.service;

import com.daimlerpoi.getmypois.entity.GeoCodeResponse;
import com.daimlerpoi.getmypois.entity.places.PlacesResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;
import java.util.Objects;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Slf4j
@Service
public class HereClient {

  private final RestTemplate restTemplate = new RestTemplate();

  @Value("${herePlacesBaseUri}")
  private String herePlacesBaseUri;

  @Value("${hereGeoCodeBaseUri}")
  private String hereGeoCodeBaseUri;

  @Cacheable(value = "hereResponse", key = "#place")
  public List<PlacesResponse> invokeHereApi(String place) {

    String location = getGeoCodeForPlace(place);
    log.info(
        "making a real time(no-cache) call to Here-GeoCode for location {} ; Place {}",
        location,
        place);

    ExecutorService executor = Executors.newFixedThreadPool(3);
    List<CompletableFuture<PlacesResponse>> futures =
        Stream.of("restaurant", "parking-facility", "ev-charging-station")
            .map(t -> CompletableFuture.supplyAsync(() -> invokeHereApi(t, location), executor))
            .collect(Collectors.toList());

    List<PlacesResponse> result =
        futures.stream().map(CompletableFuture::join).collect(Collectors.toList());
    executor.shutdown();

    return result;
  }

  private PlacesResponse invokeHereApi(String category, String location) {

    UriComponentsBuilder uriComponentsBuilder =
        UriComponentsBuilder.fromUriString(herePlacesBaseUri + "/v1/browse")
            .queryParam("at", location)
            .queryParam("cat", category)
            .queryParam("app_code", "FzyuK6KjQQGwZ88OW2umBg")
            .queryParam("app_id", "c0zGYugKrHb8Vppa8L31")
            .queryParam("size", "3");

    ResponseEntity<PlacesResponse> responseEntity =
        restTemplate.getForEntity(uriComponentsBuilder.build().toUri(), PlacesResponse.class);

    return responseEntity.getBody();
  }

  @Cacheable(value = "geoCode", key = "#placeName")
  private String getGeoCodeForPlace(String placeName) {
    log.info("making a real time(no-cache) call to Here-GeoCode for placename {}", placeName);

    UriComponentsBuilder uriComponentsBuilder =
        UriComponentsBuilder.fromUriString(hereGeoCodeBaseUri + "/v1/geocode");
    uriComponentsBuilder.queryParam("q", placeName);
    uriComponentsBuilder.queryParam("apiKey", "Dm7LTDU-_0aeGvuJ5MvyVtpU7-EyPjV9fmdpBymBRCk");
    ResponseEntity<GeoCodeResponse> responseEntity =
        restTemplate.getForEntity(uriComponentsBuilder.build().toUri(), GeoCodeResponse.class);

    GeoCodeResponse geoCodeResponse = responseEntity.getBody();
    if (Objects.isNull(geoCodeResponse)
        || Objects.isNull(geoCodeResponse.getItems())
        || Objects.isNull(geoCodeResponse.getItems().get(0))
        || Objects.isNull(geoCodeResponse.getItems().get(0).getPosition())) {
      throw new RuntimeException("Invalid Response or Invalid Location");
    }
    return String.format(
        "%s,%s",
        geoCodeResponse.getItems().get(0).getPosition().getLat(),
        geoCodeResponse.getItems().get(0).getPosition().getLng());
  }
}
