package com.demo.entregas.web.dto.carrinho;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Schema(description = "Item a adicionar no carrinho")
public class ItemCarrinhoRequest {

    @NotNull(message = "ID do produto é obrigatório")
    @Schema(description = "ID do produto", requiredMode = Schema.RequiredMode.REQUIRED)
    private Long produtoId;

    @NotNull(message = "Quantidade é obrigatória")
    @Positive(message = "Quantidade deve ser maior que zero")
    @Schema(description = "Quantidade", requiredMode = Schema.RequiredMode.REQUIRED)
    private Integer quantidade;
}
