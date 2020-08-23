package com.daimlerpoi.getmypois.entity.places;

import com.daimlerpoi.getmypois.entity.Item;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ItemV2 {
    Integer distance;
    String title;
    Float averageRating;
    Category category;
    String vicinity;
    int[] Position;

}
