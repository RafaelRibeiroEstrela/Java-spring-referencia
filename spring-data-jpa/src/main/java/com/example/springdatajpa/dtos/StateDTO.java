package com.example.springdatajpa.dtos;

import com.example.springdatajpa.models.State;

import java.io.Serializable;

public class StateDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;
    private String name;
    private String uf;

    public StateDTO() {}

    public StateDTO(State state) {
        this.id = state.getId();
        this.name = state.getName();
        this.uf = state.getUf();
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

    public String getUf() {
        return uf;
    }

    public void setUf(String uf) {
        this.uf = uf;
    }
}
