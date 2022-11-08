package com.example.gendermomondo.dtos;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@NoArgsConstructor
@ToString
public class GenderDTO {
    private String name;
    private String gender;
    private int count;
    private double probability;

    public void setProbability(double probability) {
        this.probability = probability * 100;
    }
}
