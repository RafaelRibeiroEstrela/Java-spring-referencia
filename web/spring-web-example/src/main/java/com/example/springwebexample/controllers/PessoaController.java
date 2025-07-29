package com.example.springwebexample.controllers;


import com.example.springwebexample.dto.PessoaFilter;
import com.example.springwebexample.dto.PessoaRequest;
import com.example.springwebexample.dto.PessoaResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.List;

@Tag(name = "Pessoas", description = "Endpoints para gerenciamento de pessoas")
@RestController
@RequestMapping("/pessoas")
public class PessoaController {

    @Operation(summary = "Cria uma nova pessoa", description = "Insere uma nova pessoa no sistema")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Pessoa criada com sucesso",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = PessoaResponse.class))
            ),
            @ApiResponse(responseCode = "400", description = "Dados de requisição inválidos")
    })
    @PostMapping
    public ResponseEntity<PessoaResponse> create(
            @io.swagger.v3.oas.annotations.parameters.RequestBody(description = "Dados para criação da pessoa", required = true,
                    content = @Content(schema = @Schema(implementation = PessoaRequest.class)))
            @RequestBody PessoaRequest request) {
        System.out.println("Criando uma Pessoa");
        return ResponseEntity.status(HttpStatus.CREATED).body(new PessoaResponse());
    }

    @Operation(summary = "Atualiza uma pessoa", description = "Atualiza os dados de uma pessoa existente pelo ID")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Pessoa atualizada com sucesso",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = PessoaResponse.class))
            ),
            @ApiResponse(responseCode = "400", description = "Dados de requisição inválidos"),
            @ApiResponse(responseCode = "404", description = "Pessoa não encontrada")
    })
    @PutMapping("/{id}")
    public ResponseEntity<PessoaResponse> update(
            @io.swagger.v3.oas.annotations.parameters.RequestBody(description = "Dados para atualização da pessoa", required = true,
                    content = @Content(schema = @Schema(implementation = PessoaRequest.class)))
            @Parameter(description = "ID da pessoa a ser atualizada", required = true)
            @RequestBody PessoaRequest request, @PathVariable Long id) {
        System.out.println("Atualizando uma Pessoa");
        return ResponseEntity.ok().body(new PessoaResponse());
    }

    @Operation(summary = "Deleta uma pessoa", description = "Remove a pessoa identificada pelo ID")
    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "Pessoa deletada com sucesso"),
            @ApiResponse(responseCode = "404", description = "Pessoa não encontrada")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<PessoaResponse> delete(
            @Parameter(description = "ID da pessoa a ser deletada", required = true)
            @PathVariable Long id) {
        System.out.println("Deletando uma Pessoa");
        return ResponseEntity.noContent().build();
    }

    @Operation(summary = "Busca uma pessoa por ID", description = "Retorna os dados de uma pessoa específica")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Pessoa encontrada",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = PessoaResponse.class))
            ),
            @ApiResponse(responseCode = "404", description = "Pessoa não encontrada")
    })
    @GetMapping("/{id}")
    public ResponseEntity<PessoaResponse> findById(
            @Parameter(description = "ID da pessoa buscada", required = true)
            @PathVariable Long id) {
        System.out.println("Buscando uma Pessoa");
        return ResponseEntity.ok().body(new PessoaResponse());
    }

    @Operation(summary = "Lista todas as pessoas", description = "Retorna todas as pessoas cadastradas")
    @ApiResponse(responseCode = "200", description = "Lista de pessoas retornada",
            content = @Content(mediaType = "application/json",
                    array = @ArraySchema(schema = @Schema(implementation = PessoaResponse.class)))
    )
    @GetMapping("/all")
    public ResponseEntity<List<PessoaResponse>> findAll() {
        System.out.println("Buscando pessoas");
        return ResponseEntity.ok().body(List.of(new PessoaResponse()));
    }

    @Operation(summary = "Busca pessoas com filtro e paginação", description = "Filtra e retorna uma página de pessoas")
    @ApiResponse(responseCode = "200", description = "Página de pessoas retornada",
            content = @Content(mediaType = "application/json",
                    schema = @Schema(implementation = Page.class))
    )
    @PostMapping("/filtro")
    public ResponseEntity<Page<PessoaResponse>> findAll(
            @io.swagger.v3.oas.annotations.parameters.RequestBody(description = "Critérios de filtro para busca", required = true,
                    content = @Content(schema = @Schema(implementation = PessoaFilter.class)))
            @RequestBody PessoaFilter filter,
            @ParameterObject Pageable pageable) {
        System.out.println("Buscando pessoas por filtro");
        return ResponseEntity.ok().body(new PageImpl<>(List.of(new PessoaResponse()), pageable, 10));
    }

    @Operation(
            summary = "Upload de arquivo por multipart/form-data",
            description = "Upload de arquivo por multipart/form-data."
    )
    @PostMapping(value = "/upload", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    @Transactional
    public ResponseEntity<Void> upload(@RequestPart("file") MultipartFile file, @PathVariable Long id) throws IOException {
        System.out.println("Upload de Foto de uma pessoa");
        String nomeArquivo = file.getOriginalFilename();
        String contentType = file.getContentType();
        byte[] conteudoArquivo = file.getBytes();
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @Operation(
            summary = "Download de arquivo por id em stream",
            description = "Download de arquivo por id em stream."
    )
    @GetMapping(value = "/download/{id}", produces = MediaType.APPLICATION_OCTET_STREAM_VALUE)
    @Transactional(readOnly = true)
    public ResponseEntity<Resource> download(@PathVariable Long id) {
        System.out.println("Download de Foto de uma pessoa");
        String nomeArquivo = "arquivo.txt";
        byte[] conteudoArquivo = "texto".getBytes();
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + nomeArquivo + "\"")
                .header("nome", nomeArquivo)
                .header("content-type", "text/plain")
                .header("extensao", ".txt")
                .contentType(MediaType.APPLICATION_OCTET_STREAM)
                .body(new InputStreamResource(new ByteArrayInputStream(conteudoArquivo)));
    }
}
