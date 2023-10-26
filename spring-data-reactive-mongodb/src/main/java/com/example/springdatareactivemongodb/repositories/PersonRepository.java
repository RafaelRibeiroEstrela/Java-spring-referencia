package com.example.springdatareactivemongodb.repositories;

import com.example.springdatareactivemongodb.models.Person;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import reactor.core.publisher.Flux;

public interface PersonRepository extends ReactiveMongoRepository<Person, ObjectId> {

    Flux<Person> findByAge(Integer age);
    @Query(" { 'age' : ?0 } ")
    Flux<Person> findByAgeWithQuery(Integer age);

}
