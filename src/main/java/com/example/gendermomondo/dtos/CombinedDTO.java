package com.example.gendermomondo.dtos;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@NoArgsConstructor
@ToString
public class CombinedDTO {
    private String name;
    private String gender;
    private int count;
    private double probability;
    private int age;
    private String country;
    private double countryProbability;

    public void setCountryProbability(double newProbability) {
        this.countryProbability = newProbability * 100;
    }
}
