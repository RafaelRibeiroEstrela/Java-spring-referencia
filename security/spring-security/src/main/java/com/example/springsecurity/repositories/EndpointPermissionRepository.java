package com.example.springsecurity.repositories;

import com.example.springsecurity.models.EndpointPermission;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface EndpointPermissionRepository extends JpaRepository<EndpointPermission, Long> {
    @Query("SELECT ep FROM EndpointPermission ep WHERE ep.role.authority = :authority")
    List<EndpointPermission> findByAuthority(String authority);
}
