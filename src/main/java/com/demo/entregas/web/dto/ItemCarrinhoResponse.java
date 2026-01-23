package com.demo.entregas.web.dto;

public record ItemCarrinhoResponse(
    Long id,
    Long produtoId,
    String nomeProduto,
    Integer quantidade,
    Double precoUnitario,
    Double subtotal
) {}
