package com.demo.entregas.web.dto.auth;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Schema(description = "Dados para login")
public class LoginRequest {

    @NotBlank(message = "Email é obrigatório")
    @Schema(description = "Email do usuário", example = "usuario@email.com", requiredMode = Schema.RequiredMode.REQUIRED)
    private String email;

    @NotBlank(message = "Senha é obrigatória")
    @Schema(description = "Senha do usuário", requiredMode = Schema.RequiredMode.REQUIRED)
    private String senha;
}
