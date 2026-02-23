package com.demo.entregas.security;

import com.demo.entregas.exception.RecursoNaoEncontradoException;
import com.demo.entregas.repository.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CurrentUser {

    private final UsuarioRepository usuarioRepository;

    public Long getUsuarioId() {
        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        return usuarioRepository.findByEmail(email)
                .orElseThrow(() -> new RecursoNaoEncontradoException("Usuário não encontrado"))
                .getId();
    }

    public String getEmail() {
        return SecurityContextHolder.getContext().getAuthentication().getName();
    }
}
