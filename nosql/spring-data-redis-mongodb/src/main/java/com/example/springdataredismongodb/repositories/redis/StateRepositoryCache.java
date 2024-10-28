package com.example.springdataredismongodb.repositories.redis;

import com.example.springdataredismongodb.models.redis.StateCache;
import org.springframework.data.repository.CrudRepository;

public interface StateRepositoryCache extends CrudRepository<StateCache, String> {
}
