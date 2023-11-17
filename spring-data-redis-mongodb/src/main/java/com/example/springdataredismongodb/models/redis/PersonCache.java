package com.example.springdataredismongodb.models.redis;

import com.example.springdataredismongodb.models.mongo.Address;
import org.bson.types.ObjectId;
import org.springframework.data.redis.core.RedisHash;

@RedisHash("persons")
public class PersonCache {

    private ObjectId id;
    private String name;
    private Integer age;
    private Address address;

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

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }
}
