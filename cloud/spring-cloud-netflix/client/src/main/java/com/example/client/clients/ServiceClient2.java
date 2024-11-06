package com.example.client.clients;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(name = "service2")
public interface ServiceClient2 {

    @GetMapping("/controller")
    ResponseEntity<String> getService();
}
