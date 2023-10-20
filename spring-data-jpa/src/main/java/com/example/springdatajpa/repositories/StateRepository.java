package com.example.springdatajpa.repositories;

import com.example.springdatajpa.models.State;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StateRepository extends JpaRepository<State, Long> {

    State findByUf(String uf);
}
