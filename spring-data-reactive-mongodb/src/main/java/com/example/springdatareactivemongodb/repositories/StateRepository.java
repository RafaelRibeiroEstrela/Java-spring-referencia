package com.example.springdatareactivemongodb.repositories;

import com.example.springdatareactivemongodb.models.State;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import reactor.core.publisher.Mono;

public interface StateRepository extends ReactiveMongoRepository<State, ObjectId> {
    Mono<State> findByUf(String uf);
}
