package com.example.springreferencia.repositories;

import com.example.springreferencia.models.mongodb.PersonMongoDB;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

public interface PersonReactiveMongoDBRepository extends ReactiveMongoRepository<PersonMongoDB, ObjectId> {
}
