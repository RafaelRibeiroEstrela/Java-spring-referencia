package com.example.springdataredis.caches;

import com.example.springdataredis.models.State;
import org.springframework.data.repository.CrudRepository;

import java.util.UUID;

public interface StateCache extends CrudRepository<State, String> {
}
