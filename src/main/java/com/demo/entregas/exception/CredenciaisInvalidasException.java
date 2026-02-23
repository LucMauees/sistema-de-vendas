package com.demo.entregas.exception;

public class CredenciaisInvalidasException extends RuntimeException {

    public CredenciaisInvalidasException() {
        super("Email ou senha inválidos");
    }
}
