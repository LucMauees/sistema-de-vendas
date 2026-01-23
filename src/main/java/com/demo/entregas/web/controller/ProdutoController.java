package com.demo.entregas.web.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.demo.entregas.domain.entity.Produto;
import org.springframework.http.ResponseEntity;
import com.demo.entregas.service.ProdutoService;
import com.demo.entregas.web.dto.ProdutoResponse;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "Produto Controller", description = "Endpoints para gerenciamento de produtos")
@RequestMapping("/produtos")
@RestController
public class ProdutoController {
    
    private final ProdutoService produtoService;

    public ProdutoController(ProdutoService produtoService) {
        this.produtoService = produtoService;
    }

    @Operation(summary = "Buscar Produto por ID", description = "Retorna as informações do produto pelo ID")
    @GetMapping("/{id}")
    public ResponseEntity<ProdutoResponse> buscarPorId(@PathVariable Long id) {

        Produto produto = produtoService.buscaProdutoId(id);

        ProdutoResponse response = new ProdutoResponse(
                produto.getNome(),
                produto.getDescricao(),
                produto.getPreco(),
                produto.getEstoqueAtual(),
                produto.getCategoria(),
                produto.getMarca(),
                produto.getNomeFornecedor(),
                produto.getImagemPrincipalUrl());

        return ResponseEntity.ok(response);
    }

}
