package com.demo.entregas.exception;

public class UsuarioJaExisteException extends RuntimeException {

    public UsuarioJaExisteException(String mensagem) {
        super(mensagem);
    }
}
