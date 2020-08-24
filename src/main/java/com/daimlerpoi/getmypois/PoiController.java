package com.daimlerpoi.getmypois;

import com.daimlerpoi.getmypois.entity.places.PlacesResponse;
import com.daimlerpoi.getmypois.service.HereClient;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping
@Slf4j
public class PoiController {
  @Autowired private HereClient hereClient;

  @GetMapping(
      value = "/api/v1/poi-place-management/place/pois",
      produces = MediaType.APPLICATION_JSON_VALUE)
  public List<PlacesResponse> getPoidetails(@RequestParam String location) {
    List<PlacesResponse> apiResponse = hereClient.invokeHereApi(location);
    log.info("returning response for location {}", location);
    return apiResponse;
  }
}
