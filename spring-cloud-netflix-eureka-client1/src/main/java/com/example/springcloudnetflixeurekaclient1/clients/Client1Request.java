package com.example.springcloudnetflixeurekaclient1.clients;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(name = "eureka-client2")
public interface Client1Request {

    @GetMapping("/client2")
    String getData();
}
