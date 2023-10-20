package com.example.springdatajpa.repositories;

import com.example.springdatajpa.models.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PersonRepository extends JpaRepository<Person, Long> {

    List<Person> findByAge(Integer age);

    @Query("SELECT p FROM Person p WHERE p.age = :age")
    List<Person> findByAgeWithQuery(Integer age);

    @Query(nativeQuery = true, value = "SELECT * FROM persons WHERE age = :age")
    List<Person> findByAgeWithNativeQuery(Integer age);

}
