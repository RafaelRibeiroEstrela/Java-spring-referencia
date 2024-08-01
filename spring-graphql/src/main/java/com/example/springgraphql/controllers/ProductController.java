package com.example.springgraphql.controllers;

import com.example.springgraphql.models.Product;
import com.example.springgraphql.services.ProductService;
import lombok.AllArgsConstructor;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Controller
@AllArgsConstructor
public class ProductController {

    private final ProductService service;

    @QueryMapping
    public List<Product> findAll() {
        return service.findAll();
    }

    @QueryMapping
    public List<Product> findByCategory(@Argument Long categoryId) {
        return service.findByCategory(categoryId);
    }
}
