package com.example.springwebexample.controllers;


import com.example.springwebexample.dto.PessoaFilter;
import com.example.springwebexample.dto.PessoaRequest;
import com.example.springwebexample.dto.PessoaResponse;
import com.example.springwebexample.services.PessoaService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Tag(name = "Pessoas", description = "Endpoints para gerenciamento de pessoas")
@RestController
@RequestMapping("/pessoas")
public class PessoaController {

    private final PessoaService pessoaService;

    public PessoaController(PessoaService pessoaService) {
        this.pessoaService = pessoaService;
    }

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
        return ResponseEntity.status(HttpStatus.CREATED).body(pessoaService.create(request));
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
        return ResponseEntity.ok().body(pessoaService.update(request, id));
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
        pessoaService.delete(id);
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
        return ResponseEntity.ok().body(pessoaService.findById(id));
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
        return ResponseEntity.ok().body(pessoaService.findAll(filter, pageable));
    }
}
