package com.example.springreferencia.dtos;

import com.example.springreferencia.models.PersonMongoDB;
import com.example.springreferencia.models.PersonRelational;

public class PersonDTO {

    private String id;
    private String name;
    private Integer company;
    private Integer code;

    public PersonDTO() {

    }

    public PersonDTO(PersonMongoDB person) {
        this.id = person.getId().toHexString();
        this.name = person.getName();
        this.code = person.getCode();
        this.company = person.getCompany();
    }

    public PersonDTO(PersonRelational person) {
        this.id = person.getId().toString();
        this.name = person.getName();
        this.code = person.getCode();
        this.company = person.getCompany();
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

    public Integer getCompany() {
        return company;
    }

    public void setCompany(Integer company) {
        this.company = company;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }
}
