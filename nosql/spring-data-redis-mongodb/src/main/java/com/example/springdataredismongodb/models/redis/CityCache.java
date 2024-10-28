package com.example.springdataredismongodb.models.redis;

import com.example.springdataredismongodb.models.mongo.State;
import org.bson.types.ObjectId;
import org.springframework.data.redis.core.RedisHash;

@RedisHash("cities")
public class CityCache {

    private ObjectId id;
    private String name;
    private State state;

    public ObjectId getId() {
        return id;
    }

    public void setId(ObjectId id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public State getState() {
        return state;
    }

    public void setState(State state) {
        this.state = state;
    }
}
