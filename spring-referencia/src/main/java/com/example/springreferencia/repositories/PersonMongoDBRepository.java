package com.example.springreferencia.repositories;

import com.example.springreferencia.models.mongodb.PersonMongoDB;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

// REPOSITORY PADRAO DO MVC
// maven -> spring-boot-starter-data-mongodb
public interface PersonMongoDBRepository extends MongoRepository<PersonMongoDB, ObjectId> {
}
