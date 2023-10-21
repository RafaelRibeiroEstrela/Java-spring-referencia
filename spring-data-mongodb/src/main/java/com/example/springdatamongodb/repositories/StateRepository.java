package com.example.springdatamongodb.repositories;

import com.example.springdatamongodb.models.State;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface StateRepository extends MongoRepository<State, ObjectId> {

    State findByUf(String uf);
}
