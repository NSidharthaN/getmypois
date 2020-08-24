package com.daimlerpoi.getmypois;

import com.daimlerpoi.getmypois.entity.places.PlacesResponse;
import com.daimlerpoi.getmypois.service.HereClient;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Objects;

@SpringBootTest
class GetmypoisApplicationTests {

    @Test
    void contextLoads() {
    }

    @Autowired
    HereClient hereClient;

    @Test
    public void testHereClientResponseForRestaurantCategory() {
        List<PlacesResponse> placesResponsesList = hereClient.invokeHereApi("London");
        Assertions.assertTrue(Objects.nonNull(placesResponsesList) && placesResponsesList.size() > 0);
        Assertions.assertTrue(placesResponsesList.get(0).getResults().getItems().size() > 1);
        Assertions.assertTrue(placesResponsesList.get(0).getResults().getItems().stream().anyMatch(item -> "restaurant".equalsIgnoreCase(item.getCategory().getTitle())));
    }

}
