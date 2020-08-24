package com.daimlerpoi.getmypois.entity.places;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Results {
  List<ItemV2> items;

  @JsonProperty("next")
  String nextHrefUri;
}
