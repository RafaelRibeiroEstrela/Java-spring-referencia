package com.example.springdataredismongodb.repositories.redis;

import com.example.springdataredismongodb.models.redis.PersonCache;
import com.example.springdataredismongodb.models.redis.StateCache;
import org.bson.types.ObjectId;
import org.springframework.data.repository.CrudRepository;

public interface PersonRepositoryCache extends CrudRepository<PersonCache, String> {
}
