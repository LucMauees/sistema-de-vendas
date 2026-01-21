package com.demo.entregas.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.demo.entregas.domain.entity.Produto;
import org.springframework.http.ResponseEntity;
import com.demo.entregas.service.ProdutoService;
import com.demo.entregas.web.dto.ProdutoResponse;

import org.springframework.web.bind.annotation.GetMapping;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.RequestParam;

@Tag(name = "Produto Controller", description = "Endpoints para gerenciamento de produtos")
@RequestMapping("/produtos")
@RestController
public class ProdutoController {
    @Autowired
    private final ProdutoService produtoService;

    public ProdutoController(ProdutoService produtoService) {
        this.produtoService = produtoService;
    }

    @Operation(summary = "Chamada de teste para produtos", description = "Retorna uma mensagem de teste para o endpoint de produtos")
    @GetMapping
    public ResponseEntity<String> chamada() {
        return ResponseEntity.ok("teste produto");
    }

    @Operation(summary = "Outro teste para produtos", description = "Retorna outra mensagem de teste para o endpoint de produtos")
    @GetMapping("/listarPorId")
    public ResponseEntity<ProdutoResponse> buscaProdutoId(@RequestParam long id) {

        Produto produto = produtoService.buscaProdutoId(id);

        ProdutoResponse response = new ProdutoResponse(

                produto.getNome(),
                produto.getDescricao(),
                produto.getPreco(),
                produto.getEstoqueAtual(),
                produto.getCategoria(),
                produto.getMarca(),
                produto.getNome_fornecedor(),
                produto.getImagemPrincipalurl());

        return ResponseEntity.ok(response);
    }

}
