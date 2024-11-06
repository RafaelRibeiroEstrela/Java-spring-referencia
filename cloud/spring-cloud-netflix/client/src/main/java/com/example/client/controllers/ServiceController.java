package com.example.client.controllers;

import com.example.client.clients.ServiceClient1;
import com.example.client.clients.ServiceClient2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/services")
public class ServiceController {

    @Autowired
    private ServiceClient1 service1;

    @Autowired
    private ServiceClient2 service2;

    @GetMapping("/service1")
    public ResponseEntity<String> getService1() {
        return ResponseEntity.ok(service1.getService().getBody());
    }

    @GetMapping("/service2")
    public ResponseEntity<String> getService2() {
        return ResponseEntity.ok(service2.getService().getBody());
    }
}
