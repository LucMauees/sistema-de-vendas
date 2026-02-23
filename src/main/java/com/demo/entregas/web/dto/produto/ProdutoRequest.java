package com.demo.entregas.web.dto.produto;

import com.demo.entregas.domain.enumm.statusProduto;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.*;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Schema(description = "Dados para criar/atualizar produto")
public class ProdutoRequest {

    @NotBlank
    @Schema(description = "Nome do produto", requiredMode = Schema.RequiredMode.REQUIRED)
    private String nome;

    @NotBlank
    @Schema(description = "Descrição", requiredMode = Schema.RequiredMode.REQUIRED)
    private String descricao;

    @NotBlank
    @Schema(description = "Marca", requiredMode = Schema.RequiredMode.REQUIRED)
    private String marca;

    @NotNull
    @PositiveOrZero
    @Schema(description = "Preço", requiredMode = Schema.RequiredMode.REQUIRED)
    private Double preco;

    @NotNull
    @PositiveOrZero
    @Schema(description = "Quantidade em estoque", requiredMode = Schema.RequiredMode.REQUIRED)
    private Integer estoqueAtual;

    @NotNull
    @Schema(description = "Status do produto", requiredMode = Schema.RequiredMode.REQUIRED)
    private statusProduto.Status status;

    @NotBlank
    @Schema(description = "Nome do fornecedor", requiredMode = Schema.RequiredMode.REQUIRED)
    private String nomeFornecedor;

    @NotBlank
    @Schema(description = "Categoria", requiredMode = Schema.RequiredMode.REQUIRED)
    private String categoria;

    @NotBlank
    @Schema(description = "URL da imagem principal", requiredMode = Schema.RequiredMode.REQUIRED)
    private String imagemPrincipalUrl;
}
