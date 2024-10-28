package com.example.springsecurity.services;

import com.example.springsecurity.models.EndpointPermission;
import com.example.springsecurity.repositories.EndpointPermissionRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EndpointPermissionService {

    private final EndpointPermissionRepository endpointPermissionRepository;

    public EndpointPermissionService(EndpointPermissionRepository endpointPermissionRepository) {
        this.endpointPermissionRepository = endpointPermissionRepository;
    }

    public List<EndpointPermission> getPermissionsForAuthority(String authority) {
        return endpointPermissionRepository.findByAuthority(authority);
    }

    public List<EndpointPermission> getAllPermissions() {
        return endpointPermissionRepository.findAll();
    }
}
