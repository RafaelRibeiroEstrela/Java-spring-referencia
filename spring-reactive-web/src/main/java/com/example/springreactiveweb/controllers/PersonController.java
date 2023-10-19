package com.example.springreactiveweb.controllers;

import com.example.springreactiveweb.dtos.PersonDTO;
import jakarta.validation.Valid;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.Arrays;

@RestController
@RequestMapping("/persons")
public class PersonController {

    @GetMapping
    public ResponseEntity<Flux<PersonDTO>> find() {
        Flux<PersonDTO> flux = Flux.fromIterable(Arrays.asList(new PersonDTO("Carlos", 30, LocalDate.now())));
        return ResponseEntity.status(HttpStatus.OK).body(flux);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Mono<PersonDTO>> findById(@PathVariable Long id) {
        Mono<PersonDTO> mono = Mono.just(new PersonDTO("Carlos", 30, LocalDate.now()));
        return ResponseEntity.status(HttpStatus.OK).body(mono);
    }

    @PostMapping
    public ResponseEntity<Mono<PersonDTO>> create(@RequestBody @Valid PersonDTO personDTO) {
        Mono<PersonDTO> mono = Mono.just(personDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(mono);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Mono<PersonDTO>> update(@RequestBody @Valid PersonDTO personDTO, @PathVariable Long id) {
        Mono<PersonDTO> mono = Mono.just(personDTO);
        return ResponseEntity.status(HttpStatus.OK).body(mono);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        return ResponseEntity.status(HttpStatus.OK).build();
    }


}
