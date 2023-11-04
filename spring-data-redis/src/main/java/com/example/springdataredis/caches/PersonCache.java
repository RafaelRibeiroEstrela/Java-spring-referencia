package com.example.springdataredis.caches;

import com.example.springdataredis.models.Person;

import java.util.List;

public interface PersonCache {

    List<Person> findAll(String key);
    Person findById(String key);
    Person save(Person person, String key);
    Person save(Person person, String key, long ttl);
    Person update(Person person, String key);
    Person update(Person person, String key, long ttl);
    void delete(String key);
}
