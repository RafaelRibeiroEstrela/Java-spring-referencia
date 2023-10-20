package com.example.springdatajpa.dtos;

import com.example.springdatajpa.models.Person;
import jakarta.validation.constraints.NotNull;

import java.io.Serializable;

public class PersonDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;
    @NotNull(message = "Nome é obrigatorio.")
    private String name;
    @NotNull(message = "Idade é obrigatorio.")
    private Integer age;
    @NotNull(message = "Endereço é obrigatorio.")
    private AddressDTO address;

    public PersonDTO() {}

    public PersonDTO(Person person) {
        this.id = person.getId();
        this.name = person.getName();
        this.age = person.getAge();
        this.address = new AddressDTO(person.getAddress());
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

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public AddressDTO getAddress() {
        return address;
    }

    public void setAddress(AddressDTO address) {
        this.address = address;
    }

}
