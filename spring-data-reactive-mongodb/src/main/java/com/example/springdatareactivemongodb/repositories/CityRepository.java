package com.example.springdatareactivemongodb.repositories;

import com.example.springdatareactivemongodb.models.City;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import reactor.core.publisher.Flux;

public interface CityRepository extends ReactiveMongoRepository<City, ObjectId> {

    @Query(" { 'state._id' : ?0 } ")
    Flux<City> findByStateId(ObjectId id);
}
