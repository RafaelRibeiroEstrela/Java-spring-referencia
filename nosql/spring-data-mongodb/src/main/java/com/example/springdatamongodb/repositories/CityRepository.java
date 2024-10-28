package com.example.springdatamongodb.repositories;

import com.example.springdatamongodb.models.City;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;

public interface CityRepository extends MongoRepository<City, ObjectId> {

    @Query(" { 'state._id' : ?0 } ")
    List<City> findByStateId(ObjectId id);
}
