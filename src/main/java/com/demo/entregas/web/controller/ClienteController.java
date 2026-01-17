package com.demo.entregas.web.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

import org.springframework.web.bind.annotation.GetMapping;

@Tag(name = "Cliente Controller", description = "Endpoints para gerenciamento de clientes")
@RequestMapping("/cliente")
@RestController
public class clienteController {
    
    @Operation(summary = "Chamada de teste para clientes", description = "Retorna uma mensagem de teste para o endpoint de clientes")
    @GetMapping
    public ResponseEntity<String> teste(){
        return ResponseEntity.ok("teste");
    }
}


