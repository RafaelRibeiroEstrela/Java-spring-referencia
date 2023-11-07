package com.example.springdataredis.caches.impl;

import com.example.springdataredis.caches.PersonCache;
import com.example.springdataredis.components.CacheComponent;
import com.example.springdataredis.models.Person;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Repository
public class PersonCacheImpl implements PersonCache {

    private final CacheComponent cache;

    private static final String CACHE_KEY = "persons";

    public PersonCacheImpl(CacheComponent cache) {
        this.cache = cache;
    }


    @Override
    public List<Person> findAll() {
        //CAST de Object para String, pois o conjunto de elementos contem apenas String
        Set<String> set = cache.findSetByKey(CACHE_KEY).stream().map(obj -> (String) obj).collect(Collectors.toSet());
        List<Object> list = new ArrayList<>();
        set.forEach(obj -> {
            list.add(cache.findValueByKey(obj));
        });
        //CAST de Object para Person, pois o value é do tipo Person
        return list.stream().map(obj -> (Person) obj).toList();
    }

    @Override
    public Person findById(String key) {
        return (Person) cache.findValueByKey(formatKey(key));
    }

    @Override
    public Person save(Person person, String key) {
        cache.save(formatKey(key), person);
        cache.saveValueInSet(CACHE_KEY, formatKey(key));
        return (Person) cache.findValueByKey(formatKey(key));
    }

    @Override
    public Person save(Person person, String key, long ttl) {
        cache.save(formatKey(key), person, ttl);
        cache.saveValueInSet(CACHE_KEY, formatKey(key));
        return (Person) cache.findValueByKey(formatKey(key));
    }

    @Override
    public Person update(Person person, String key) {
        cache.save(formatKey(key), person);
        return (Person) cache.findValueByKey(formatKey(key));
    }

    @Override
    public Person update(Person person, String key, long ttl) {
        cache.save(formatKey(key), person, ttl);
        return (Person) cache.findValueByKey(formatKey(key));
    }

    @Override
    public void delete(String key) {
        cache.expireKey(formatKey(key));
        cache.removeValueFromSet(CACHE_KEY, formatKey(key));
    }

    private String formatKey(String key) {
        return CACHE_KEY + "::" + key;
    }
}
