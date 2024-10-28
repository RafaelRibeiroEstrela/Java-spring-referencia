package com.example.springdataredismongodb.models.redis;

import org.bson.types.ObjectId;
import org.springframework.data.redis.core.RedisHash;

@RedisHash("states")
public class StateCache {

    private ObjectId id;
    private String name;
    private String uf;

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

    public String getUf() {
        return uf;
    }

    public void setUf(String uf) {
        this.uf = uf;
    }
}
