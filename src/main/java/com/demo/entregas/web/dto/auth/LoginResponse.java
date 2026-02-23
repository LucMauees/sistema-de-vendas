package com.demo.entregas.web.dto.auth;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Schema(description = "Resposta do login com token JWT")
public class LoginResponse {

    @Schema(description = "Token JWT para uso no header Authorization: Bearer <token>")
    private String token;

    @Schema(description = "Tipo do token")
    private String tipo;

    @Schema(description = "ID do usuário")
    private Long usuarioId;

    @Schema(description = "Email do usuário")
    private String email;

    public static LoginResponse of(String token, Long usuarioId, String email) {
        return LoginResponse.builder()
                .token(token)
                .tipo("Bearer")
                .usuarioId(usuarioId)
                .email(email)
                .build();
    }
}
