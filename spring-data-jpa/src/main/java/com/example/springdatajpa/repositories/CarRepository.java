package com.example.springdatajpa.repositories;

import com.example.springdatajpa.models.Car;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CarRepository extends JpaRepository<Car, Long> {

    @Query("SELECT c FROM Car c WHERE c.person.id = :personId")
    List<Car> findByPersonId(Long personId);
}
