package com.example.springkafka.controllers;

import com.example.springkafka.models.Message;
import com.example.springkafka.producers.MessageProducer;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/producer")
public class ProducerController {

    private final MessageProducer producer;

    public ProducerController(MessageProducer producer) {
        this.producer = producer;
    }

    @PostMapping
    public ResponseEntity<String> producer(@RequestBody Message message) {
        return ResponseEntity.status(HttpStatus.CREATED).body(producer.sendMessage(message));
    }
}
