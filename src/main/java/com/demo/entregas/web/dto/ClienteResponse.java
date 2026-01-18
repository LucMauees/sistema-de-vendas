package com.demo.entregas.web.dto;

public record ClienteResponse(
    Long id,
    String nomeCompleto,
    String email,
    String telefone,
    String CPF
) {} 