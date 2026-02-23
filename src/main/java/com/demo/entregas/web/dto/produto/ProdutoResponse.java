package com.demo.entregas.web.dto.produto;

import com.demo.entregas.domain.enumm.statusProduto;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Schema(description = "Dados do produto")
public class ProdutoResponse {

    @Schema(description = "ID do produto")
    private Long id;

    @Schema(description = "Nome")
    private String nome;

    @Schema(description = "Descrição")
    private String descricao;

    @Schema(description = "Marca")
    private String marca;

    @Schema(description = "Preço")
    private Double preco;

    @Schema(description = "Estoque atual")
    private Integer estoqueAtual;

    @Schema(description = "Status")
    private statusProduto.Status status;

    @Schema(description = "Fornecedor")
    private String nomeFornecedor;

    @Schema(description = "Categoria")
    private String categoria;

    @Schema(description = "URL da imagem")
    private String imagemPrincipalUrl;
}
