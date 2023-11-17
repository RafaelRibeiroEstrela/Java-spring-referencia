package com.example.springdataredismongodb.repositories.mongo;

import com.example.springdataredismongodb.models.mongo.Person;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;

public interface PersonRepository extends MongoRepository<Person, ObjectId> {

    List<Person> findByAge(Integer age);

    @Query(" { 'age' : ?0 } ")
    List<Person> findByAgeWithQuery(Integer age);

}
