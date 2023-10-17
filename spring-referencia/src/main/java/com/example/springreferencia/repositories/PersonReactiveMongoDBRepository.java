package com.example.springreferencia.repositories;

import com.example.springreferencia.models.PersonMongoDB;
import com.example.springreferencia.models.PersonRelational;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.data.r2dbc.repository.Query;
import reactor.core.publisher.Flux;

// REPOSITORY REATIVO
// maven -> spring-boot-starter-data-mongodb-reactive
public interface PersonReactiveMongoDBRepository extends ReactiveMongoRepository<PersonMongoDB, ObjectId> {

    @Query("{ company : ?0 }")
    Flux<PersonMongoDB> findByCompany(Integer company);

}
