package com.example.springdatajpa.repositories;

import com.example.springdatajpa.models.Address;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AddressRepository extends JpaRepository<Address, Long> {
}
