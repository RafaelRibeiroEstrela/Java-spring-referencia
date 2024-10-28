package com.example.azurecosmosdb.repositories;

import com.azure.spring.data.cosmos.repository.CosmosRepository;
import com.azure.spring.data.cosmos.repository.Query;
import com.example.azurecosmosdb.models.User;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends CosmosRepository<User, String> {

    @Query("""
               SELECT *
               FROM u 
               WHERE UPPER(u.firstName) LIKE CONCAT(CONCAT('%', UPPER(@name)), '%')
            """)
    List<User> findByFirstName(String name);
}