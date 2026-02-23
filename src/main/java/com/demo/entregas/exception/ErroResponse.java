package com.demo.entregas.exception;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

import java.time.Instant;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ErroResponse {

    private Instant timestamp;
    private int status;
    private String error;
    private String message;
    private String path;
    private List<CampoErro> erros;

    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class CampoErro {
        private String campo;
        private String mensagem;
    }
}
