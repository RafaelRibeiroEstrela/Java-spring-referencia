package com.example.springweb.dtos;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

import java.time.LocalDate;

public record PersonDTO(@NotNull(message = "Nome é obrigatorio") String name,
                        @NotNull(message = "Nome é obrigatorio") Integer age,
                        @JsonFormat(pattern = "dd/MM/yyyy") LocalDate date) {
}
