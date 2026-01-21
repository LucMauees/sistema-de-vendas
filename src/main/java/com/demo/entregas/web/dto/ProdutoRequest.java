package com.demo.entregas.web.dto;

import jakarta.validation.constraints.NotBlank;

public record ProdutoRequest (
   @NotBlank String nome,
     String descricao,
     Double preco,
     Integer estoqueAtual,
     String categoria,
     String marca,
     String nome_fornecedor
){}
