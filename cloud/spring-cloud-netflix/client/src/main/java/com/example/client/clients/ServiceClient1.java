package com.example.client.clients;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(name = "service1")
public interface ServiceClient1 {

    @GetMapping("/controller")
    ResponseEntity<String> getService();
}
