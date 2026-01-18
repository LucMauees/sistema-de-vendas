package com.demo.entregas.web.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@Tag(name = "Cliente Controller", description = "Endpoints para gerenciamento de clientes")
@RequestMapping("/cliente")
@RestController
public class ClienteController {

    @Operation(summary = "Chamada de teste para clientes", description = "Retorna uma mensagem de teste para o endpoint de clientes")
    @GetMapping
    public ResponseEntity<String> teste(){
        return ResponseEntity.ok("teste");
    }

    @PostMapping("/cadastro") 
    public String postMethodName(@RequestBody String entity) {
        //TODO: process POST request
        
        return entity;
    }
    
    public ClienteController CadastroCliente() {
        // Lógica para cadastrar um cliente
        return new ClienteController();
    }
}


