package com.adobe.romannumerals.Dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class RomanNumeralDto {
    private String input;
    private String output;

}
