package com.daimlerpoi.getmypois.entity.places;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class OpeningHours {
    String text;
    Boolean isOpen;
}
