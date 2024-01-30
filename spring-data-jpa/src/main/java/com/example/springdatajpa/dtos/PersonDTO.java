package com.example.springdatajpa.dtos;

import com.example.springdatajpa.models.Car;
import com.example.springdatajpa.models.House;
import com.example.springdatajpa.models.Person;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class PersonDTO {

    private Long id;
    private String name;
    private Integer age;
    private List<HouseDTO> houses = new ArrayList<>();
    private List<CarDTO> cars = new ArrayList<>();

    public PersonDTO(Person person) {
        this.id = person.getId();
        this.name = person.getName();
        this.age = person.getAge();
    }

    public PersonDTO(Person person, List<House> houses, List<Car> cars) {
        this(person);
        this.houses.addAll(houses.stream().map(HouseDTO::new).toList());
        this.cars.addAll(cars.stream().map(CarDTO::new).toList());
    }

    public PersonDTO(Person person, List<House> houses) {
        this(person);
        this.houses.addAll(houses.stream().map(HouseDTO::new).toList());
    }
}
