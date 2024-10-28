package com.example.springdatajpa.repositories;

import com.example.springdatajpa.models.House;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HouseRepository extends JpaRepository<House, Long> {
}
