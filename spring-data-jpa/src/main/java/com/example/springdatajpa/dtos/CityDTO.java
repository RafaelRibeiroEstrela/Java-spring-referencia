package com.example.springdatajpa.dtos;

import com.example.springdatajpa.models.City;

import java.io.Serializable;

public class CityDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;
    private String name;
    private StateDTO state;

    public CityDTO() {}

    public CityDTO(City city) {
        this.id = city.getId();
        this.name = city.getName();
        this.state = new StateDTO(city.getState());
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public StateDTO getState() {
        return state;
    }

    public void setState(StateDTO state) {
        this.state = state;
    }
}
