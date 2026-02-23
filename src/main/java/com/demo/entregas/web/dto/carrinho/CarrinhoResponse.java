package com.demo.entregas.web.dto.carrinho;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Schema(description = "Carrinho do usuário com itens")
public class CarrinhoResponse {

    @Schema(description = "ID do carrinho")
    private Long id;

    @Schema(description = "Itens do carrinho")
    private List<ItemCarrinhoResponse> itens;

    @Schema(description = "Valor total do carrinho")
    private Double total;
}
