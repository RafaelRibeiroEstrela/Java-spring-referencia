package com.example.springreferencia.dtos;

import com.example.springreferencia.models.mongodb.PersonMongoDB;

public class PersonDTO {

    private String id;
    private String name;
    private int age;

    public PersonDTO() {

    }

    public PersonDTO(PersonMongoDB personMongoDB) {
        this.id = personMongoDB.getId().toHexString();
        this.name = personMongoDB.getPerson().getName();
        this.age = personMongoDB.getPerson().getAge();
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

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "PersonDTO{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}
