package com.demo.entregas.exception;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.Instant;
import java.util.List;
import java.util.stream.Collectors;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(UsuarioJaExisteException.class)
    public ResponseEntity<ErroResponse> handleUsuarioJaExiste(UsuarioJaExisteException e, HttpServletRequest request) {
        return buildResponse(HttpStatus.CONFLICT, e.getMessage(), request.getRequestURI());
    }

    @ExceptionHandler(CredenciaisInvalidasException.class)
    public ResponseEntity<ErroResponse> handleCredenciaisInvalidas(CredenciaisInvalidasException e, HttpServletRequest request) {
        return buildResponse(HttpStatus.UNAUTHORIZED, e.getMessage(), request.getRequestURI());
    }

    @ExceptionHandler(BadCredentialsException.class)
    public ResponseEntity<ErroResponse> handleBadCredentials(BadCredentialsException e, HttpServletRequest request) {
        return buildResponse(HttpStatus.UNAUTHORIZED, "Email ou senha inválidos", request.getRequestURI());
    }

    @ExceptionHandler(RecursoNaoEncontradoException.class)
    public ResponseEntity<ErroResponse> handleRecursoNaoEncontrado(RecursoNaoEncontradoException e, HttpServletRequest request) {
        return buildResponse(HttpStatus.NOT_FOUND, e.getMessage(), request.getRequestURI());
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErroResponse> handleValidacao(MethodArgumentNotValidException e, HttpServletRequest request) {
        List<ErroResponse.CampoErro> erros = e.getBindingResult().getFieldErrors().stream()
                .map(this::toCampoErro)
                .collect(Collectors.toList());
        ErroResponse body = ErroResponse.builder()
                .timestamp(Instant.now())
                .status(HttpStatus.BAD_REQUEST.value())
                .error("Bad Request")
                .message("Erro de validação")
                .path(request.getRequestURI())
                .erros(erros)
                .build();
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(body);
    }

    private ResponseEntity<ErroResponse> buildResponse(HttpStatus status, String message, String path) {
        ErroResponse body = ErroResponse.builder()
                .timestamp(Instant.now())
                .status(status.value())
                .error(status.getReasonPhrase())
                .message(message)
                .path(path)
                .build();
        return ResponseEntity.status(status).body(body);
    }

    private ErroResponse.CampoErro toCampoErro(FieldError fe) {
        return new ErroResponse.CampoErro(fe.getField(), fe.getDefaultMessage());
    }
}
