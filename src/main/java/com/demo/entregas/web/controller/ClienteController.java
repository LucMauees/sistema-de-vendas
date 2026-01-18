package com.demo.entregas.web.controller;

import java.net.URI;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.demo.entregas.domain.entity.Cliente;
import com.demo.entregas.web.dto.ClienteRequest;
import com.demo.entregas.web.dto.ClienteResponse;
import com.demo.entregas.service.ClienteService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import jakarta.validation.Valid;

@Tag(name = "Cliente Controller", description = "Endpoints para gerenciamento de clientes")
@RequestMapping("/cliente")
@RestController
public class ClienteController {

    private final ClienteService clienteService;

    public ClienteController(ClienteService clienteService) {
        this.clienteService = clienteService;
    }

    @Operation(summary = "Chamada de teste para clientes", description = "Retorna uma mensagem de teste para o endpoint de clientes")
    @GetMapping
    public ResponseEntity<String> teste() {
        return ResponseEntity.ok("teste");
    }

    @PostMapping("/cadastro")
    public ResponseEntity<ClienteResponse> cadastrarCliente(@RequestBody @Valid ClienteRequest request) {
        Cliente clienteCadastrado = clienteService.cadastrar(request);

        ClienteResponse response = new ClienteResponse(
                clienteCadastrado.getId(),
                clienteCadastrado.getNomeCompleto(),
                clienteCadastrado.getEmail(),
                clienteCadastrado.getTelefone(),
                clienteCadastrado.getCPF()
        );
        URI locaton = URI.create("/cliente/" + clienteCadastrado.getId());
        return ResponseEntity.created(locaton).body(response);
    }

}
