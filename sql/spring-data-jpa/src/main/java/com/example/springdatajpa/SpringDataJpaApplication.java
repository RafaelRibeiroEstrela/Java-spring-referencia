package com.example.springdatajpa;

import com.example.springdatajpa.dtos.PersonDTO;
import com.example.springdatajpa.models.Person;
import com.example.springdatajpa.repositories.CarRepository;
import com.example.springdatajpa.repositories.HouseRepository;
import com.example.springdatajpa.repositories.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@SpringBootApplication
public class SpringDataJpaApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(SpringDataJpaApplication.class, args);
	}

	@Autowired
	PersonRepository personRepository;
	@Autowired
	CarRepository carRepository;
	@Autowired
	HouseRepository houseRepository;

	@Override
	@Transactional
	public void run(String... args) throws Exception {
		chamarComTransactional();
		chamarSemTransactional();
	}


	public void chamarComTransactional() {
		System.out.println(personRepository.findAll().stream().map(obj -> new PersonDTO(obj, obj.getHouses(), obj.getCars())).toList());
	}

	public void chamarSemTransactional() {
		System.out.println(personRepository.findAll().stream().map(obj -> new PersonDTO(obj, obj.getHouses())).toList());
	}
}
