package com.example.springgraphqlexample.dto;

import java.util.List;

public record PessoaPaginada(
        List<PessoaResponse> content,
        int totalElements,
        int totalPages,
        int pageNumber,
        int pageSize
) {}