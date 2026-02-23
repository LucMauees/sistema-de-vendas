package com.demo.entregas.web.controller;

import com.demo.entregas.service.ProdutoService;
import com.demo.entregas.web.dto.produto.ProdutoRequest;
import com.demo.entregas.web.dto.produto.ProdutoResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/produtos")
@RequiredArgsConstructor
@Tag(name = "Produtos", description = "CRUD de produtos")
@SecurityRequirement(name = "bearerAuth")
public class ProdutoController {

    private final ProdutoService produtoService;

    @GetMapping
    @Operation(summary = "Listar todos os produtos")
    public ResponseEntity<List<ProdutoResponse>> listar() {
        return ResponseEntity.ok(produtoService.listarTodos());
    }

    @GetMapping("/{id}")
    @Operation(summary = "Buscar produto por ID")
    public ResponseEntity<ProdutoResponse> buscarPorId(@PathVariable Long id) {
        return ResponseEntity.ok(produtoService.buscarPorId(id));
    }

    @PostMapping
    @Operation(summary = "Criar produto")
    public ResponseEntity<ProdutoResponse> criar(@Valid @RequestBody ProdutoRequest request) {
        ProdutoResponse response = produtoService.criar(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Atualizar produto")
    public ResponseEntity<ProdutoResponse> atualizar(@PathVariable Long id, @Valid @RequestBody ProdutoRequest request) {
        return ResponseEntity.ok(produtoService.atualizar(id, request));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Excluir produto")
    public ResponseEntity<Void> excluir(@PathVariable Long id) {
        produtoService.excluir(id);
        return ResponseEntity.noContent().build();
    }
}
