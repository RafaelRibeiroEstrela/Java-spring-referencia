package com.example.springdataredismongodb.dtos;

import com.example.springdataredismongodb.models.mongo.State;

import java.io.Serializable;

public class StateDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    private String id;
    private String name;
    private String uf;

    public StateDTO() {}

    public StateDTO(State state) {
        this.id = state.getId().toHexString();
        this.name = state.getName();
        this.uf = state.getUf();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
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
