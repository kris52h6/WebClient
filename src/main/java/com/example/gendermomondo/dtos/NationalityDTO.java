package com.example.gendermomondo.dtos;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;

@Setter
@Getter
@NoArgsConstructor
public class NationalityDTO {
    public ArrayList<Country> country;
    private String name;
}
