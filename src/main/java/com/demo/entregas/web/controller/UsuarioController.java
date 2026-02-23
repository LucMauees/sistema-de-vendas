package com.demo.entregas.web.controller;

import com.demo.entregas.security.CurrentUser;
import com.demo.entregas.service.UsuarioService;
import com.demo.entregas.web.dto.usuario.UsuarioResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/usuarios")
@RequiredArgsConstructor
@Tag(name = "Usuários", description = "Dados do usuário autenticado")
@SecurityRequirement(name = "bearerAuth")
public class UsuarioController {

    private final UsuarioService usuarioService;
    private final CurrentUser currentUser;

    @GetMapping("/me")
    @Operation(summary = "Perfil do usuário logado")
    public ResponseEntity<UsuarioResponse> me() {
        Long usuarioId = currentUser.getUsuarioId();
        return ResponseEntity.ok(usuarioService.buscarPorId(usuarioId));
    }

    @GetMapping("/{id}")
    @Operation(summary = "Buscar usuário por ID")
    public ResponseEntity<UsuarioResponse> buscarPorId(@PathVariable Long id) {
        return ResponseEntity.ok(usuarioService.buscarPorId(id));
    }
}
