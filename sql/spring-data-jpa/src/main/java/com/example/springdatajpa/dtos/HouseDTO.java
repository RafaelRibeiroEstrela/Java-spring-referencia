package com.example.springdatajpa.dtos;

import com.example.springdatajpa.models.House;
import lombok.Data;

@Data
public class HouseDTO {

    private Long id;
    private String number;

    public HouseDTO(House house) {
        this.id = house.getId();
        this.number = house.getNumber();
    }
}
