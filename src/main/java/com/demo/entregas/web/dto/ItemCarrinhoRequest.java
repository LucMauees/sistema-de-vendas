package com.demo.entregas.web.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public record ItemCarrinhoRequest(
    @NotNull(message = "ID do produto é obrigatório")
    @Positive(message = "ID do produto deve ser positivo")
    Long produtoId,
    
    @NotNull(message = "Quantidade é obrigatória")
    @Positive(message = "Quantidade deve ser positiva")
    Integer quantidade
) {}
