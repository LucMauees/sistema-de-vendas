package com.demo.entregas.web.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "Produto Controller", description = "Endpoints para gerenciamento de produtos")
@RequestMapping("/produtos")
@RestController
public class produtoController {
    
    @Operation(summary = "Chamada de teste para produtos", description = "Retorna uma mensagem de teste para o endpoint de produtos")
    @GetMapping
    public ResponseEntity<String> chamada() {
        return ResponseEntity.ok("teste produto");
    }
}
