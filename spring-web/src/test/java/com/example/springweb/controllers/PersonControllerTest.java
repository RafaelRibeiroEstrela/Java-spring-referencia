package com.example.springweb.controllers;

import com.example.springweb.dtos.PersonDTO;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Arrays;

@ExtendWith(SpringExtension.class)
class PersonControllerTest {

    @InjectMocks
    private PersonController controller;

    @Test
    void testFind() {
        ResponseEntity responseEntity = ResponseEntity.status(HttpStatus.OK).body(Arrays.asList(new PersonDTO("Carlos", 30)));
        Assertions.assertEquals(controller.find(), responseEntity);
    }
}
