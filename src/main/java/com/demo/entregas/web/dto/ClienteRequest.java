package com.demo.entregas.web.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Email;
public record ClienteRequest (
    @NotBlank String nomeCompleto,
    @NotBlank @Email String email,
    @NotBlank String senha,
    @NotBlank String telefone,
    @NotBlank String CPF
) {}
