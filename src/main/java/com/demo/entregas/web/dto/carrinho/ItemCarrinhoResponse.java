package com.demo.entregas.web.dto.carrinho;

import com.demo.entregas.web.dto.produto.ProdutoResponse;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Schema(description = "Item do carrinho com dados do produto")
public class ItemCarrinhoResponse {

    @Schema(description = "ID do item no carrinho")
    private Long id;

    @Schema(description = "Produto")
    private ProdutoResponse produto;

    @Schema(description = "Quantidade")
    private Integer quantidade;

    @Schema(description = "Subtotal (preço x quantidade)")
    private Double subtotal;
}
