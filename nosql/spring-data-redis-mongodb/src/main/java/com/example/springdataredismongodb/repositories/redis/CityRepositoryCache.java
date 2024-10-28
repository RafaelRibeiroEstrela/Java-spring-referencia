package com.example.springdataredismongodb.repositories.redis;

import com.example.springdataredismongodb.models.redis.CityCache;
import com.example.springdataredismongodb.models.redis.StateCache;
import org.bson.types.ObjectId;
import org.springframework.data.repository.CrudRepository;

public interface CityRepositoryCache extends CrudRepository<CityCache, String> {
}
