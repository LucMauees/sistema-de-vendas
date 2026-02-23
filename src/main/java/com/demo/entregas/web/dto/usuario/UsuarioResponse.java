package com.demo.entregas.web.dto.usuario;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Schema(description = "Dados do usuário (sem senha)")
public class UsuarioResponse {

    @Schema(description = "ID do usuário")
    private Long id;

    @Schema(description = "Nome")
    private String nome;

    @Schema(description = "Email")
    private String email;

    @Schema(description = "CPF")
    private String cpf;
}
