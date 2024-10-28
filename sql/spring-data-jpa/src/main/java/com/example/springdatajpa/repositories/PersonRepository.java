package com.example.springdatajpa.repositories;

import com.example.springdatajpa.models.Person;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PersonRepository extends JpaRepository<Person, Long> {

}
