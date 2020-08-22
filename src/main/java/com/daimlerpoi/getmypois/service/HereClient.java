package com.daimlerpoi.getmypois.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import javax.annotation.PostConstruct;
import java.util.Objects;

@Service
public class HereClient {

    private RestTemplate restTemplate = new RestTemplate();

    @PostConstruct
    public void invokeHereApi() {

        String url = "https://places.ls.hereapi.com/places/v1/browse?at=48.8465,2.3722&cat=restaurant%2Cparking-facility%2Cev-charging-station&Accept-Language=en-US%2Cen%3Bq%3D0.9&app_id=c0zGYugKrHb8Vppa8L31&app_code=FzyuK6KjQQGwZ88OW2umBg";
        ResponseEntity<String> responseEntity = restTemplate.getForEntity(url, String.class);
        Objects.nonNull(responseEntity);
        System.out.println("Hey there \n \n"+responseEntity.getBody());
    }


}
