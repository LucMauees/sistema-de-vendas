package com.demo.entregas.web.dto.auth;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Schema(description = "Dados para cadastro de novo usuário")
public class RegistrarRequest {

    @NotBlank(message = "Nome é obrigatório")
    @Schema(description = "Nome completo", example = "João Silva", requiredMode = Schema.RequiredMode.REQUIRED)
    private String nome;

    @NotBlank(message = "Email é obrigatório")
    @Email(message = "Email inválido")
    @Schema(description = "Email", example = "joao@email.com", requiredMode = Schema.RequiredMode.REQUIRED)
    private String email;

    @NotBlank(message = "Senha é obrigatória")
    @Size(min = 6, message = "Senha deve ter no mínimo 6 caracteres")
    @Schema(description = "Senha (mínimo 6 caracteres)", requiredMode = Schema.RequiredMode.REQUIRED)
    private String senha;

    @NotBlank(message = "CPF é obrigatório")
    @Size(min = 11, max = 14)
    @Schema(description = "CPF (apenas números ou formatado)", example = "12345678900", requiredMode = Schema.RequiredMode.REQUIRED)
    private String cpf;
}
