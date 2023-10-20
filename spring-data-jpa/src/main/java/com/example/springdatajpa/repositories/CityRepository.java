package com.example.springdatajpa.repositories;

import com.example.springdatajpa.models.City;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CityRepository extends JpaRepository<City, Long> {

    @Query("SELECT c FROM City c WHERE c.state.id = :id")
    List<City> findByStateId(Long id);
}
