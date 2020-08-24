package com.daimlerpoi.getmypois.entity.places;

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
