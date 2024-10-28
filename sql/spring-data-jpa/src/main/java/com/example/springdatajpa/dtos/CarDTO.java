package com.example.springdatajpa.dtos;

import com.example.springdatajpa.models.Car;
import lombok.Data;

@Data
public class CarDTO {

    private Long id;
    private String name;

    public CarDTO(Car car) {
        this.id = car.getId();
        this.name = car.getName();
    }
}
