package com.demo.entregas.web.controller;

import com.demo.entregas.service.AuthService;
import com.demo.entregas.web.dto.auth.LoginRequest;
import com.demo.entregas.web.dto.auth.LoginResponse;
import com.demo.entregas.web.dto.auth.RegistrarRequest;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
@Tag(name = "Autenticação", description = "Login e registro de usuários (público)")
public class AuthController {

    private final AuthService authService;

    @PostMapping("/registrar")
    @Operation(summary = "Registrar novo usuário", description = "Cria usuário e carrinho. Retorna token JWT.")
    public ResponseEntity<LoginResponse> registrar(@Valid @RequestBody RegistrarRequest request) {
        LoginResponse response = authService.registrar(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PostMapping("/login")
    @Operation(summary = "Login", description = "Retorna token JWT. Use no header: Authorization: Bearer <token>")
    public ResponseEntity<LoginResponse> login(@Valid @RequestBody LoginRequest request) {
        return ResponseEntity.ok(authService.login(request));
    }
}
