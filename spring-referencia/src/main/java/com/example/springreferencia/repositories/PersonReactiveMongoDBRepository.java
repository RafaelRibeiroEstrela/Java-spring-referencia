package com.example.springreferencia.repositories;

import com.example.springreferencia.models.mongodb.PersonMongoDB;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

// REPOSITORY REATIVO
// maven -> spring-boot-starter-data-mongodb-reactive
public interface PersonReactiveMongoDBRepository extends ReactiveMongoRepository<PersonMongoDB, ObjectId> {
}
