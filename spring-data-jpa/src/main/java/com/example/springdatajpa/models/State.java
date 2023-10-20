package com.example.springdatajpa.models;

import jakarta.persistence.*;

import java.io.Serializable;

@Entity
@Table(name = "states")
public class State implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    private Long id;

    @Column(unique = true, nullable = false)
    private String name;

    @Column(unique = true, nullable = false)
    private String uf;

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
