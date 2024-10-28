package com.example.springdataredismongodb.repositories.mongo;

import com.example.springdataredismongodb.models.mongo.State;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface StateRepository extends MongoRepository<State, ObjectId> {

    State findByUf(String uf);
}
