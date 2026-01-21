package com.demo.entregas.web.dto;

public record ProdutoResponse(
        String nome,
        String descricao,
        Double preco,
        Integer estoqueAtual,
        String categoria,
        String marca,
        String nome_fornecedor,
        String ImagemPrincipalurl

) {
}
