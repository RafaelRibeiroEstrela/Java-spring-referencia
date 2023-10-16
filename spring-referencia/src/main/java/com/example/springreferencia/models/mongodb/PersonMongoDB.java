package com.example.springreferencia.models.mongodb;

import com.example.springreferencia.models.Person;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Document(collection = "persons")
public class PersonMongoDB {

    @Id
    @Field(name = "_id")
    private ObjectId id;

    private Person person;

    public ObjectId getId() {
        return id;
    }

    public void setId(ObjectId id) {
        this.id = id;
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }
}
