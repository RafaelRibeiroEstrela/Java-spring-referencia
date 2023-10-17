package com.example.springreferencia.repositories;

import com.example.springreferencia.models.PersonRelational;
import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.r2dbc.repository.R2dbcRepository;
import reactor.core.publisher.Flux;

// REPOSITORY REATIVO
// maven -> spring-boot-starter-data-mongodb-reactive
public interface PersonReactiveRelationalRepository extends R2dbcRepository<PersonRelational, Long> {

    @Query("SELECT * FROM persons WHERE company = :company")
    Flux<PersonRelational> findByCompany(Integer company);
}
