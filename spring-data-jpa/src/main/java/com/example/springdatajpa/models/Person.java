package com.example.springdatajpa.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "persons")
@Getter
@Setter
public class Person implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private Integer age;
    //BUSCA PESADA - SEMPRE VAI TRAZER OS DADOS
    @OneToMany(mappedBy = "person", fetch = FetchType.EAGER)
    private List<House> houses = new ArrayList<>();
    //BUSCA PREGUICOSA - VAI TRAZER OS DADOS DE FORMA EXPLICITA
    @OneToMany(mappedBy = "person", fetch = FetchType.LAZY)
    private List<Car> cars = new ArrayList<>();

    //AO CHAMAR O METODO toString COM O RELACIONAMENTO LAZY, PODERA OCORRER ERRO CASO NAO ESTEJA NA MESMA TRANSACAO
}
