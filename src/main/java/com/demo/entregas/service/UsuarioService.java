package com.demo.entregas.service;

import com.demo.entregas.domain.entity.Usuario;
import com.demo.entregas.exception.RecursoNaoEncontradoException;
import com.demo.entregas.repository.UsuarioRepository;
import com.demo.entregas.web.dto.usuario.UsuarioResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;

    public UsuarioResponse buscarPorId(Long id) {
        Usuario usuario = usuarioRepository.findById(id)
                .orElseThrow(() -> new RecursoNaoEncontradoException("Usuário não encontrado"));
        return toResponse(usuario);
    }

    public UsuarioResponse buscarPorEmail(String email) {
        Usuario usuario = usuarioRepository.findByEmail(email)
                .orElseThrow(() -> new RecursoNaoEncontradoException("Usuário não encontrado"));
        return toResponse(usuario);
    }

    public static UsuarioResponse toResponse(Usuario u) {
        return UsuarioResponse.builder()
                .id(u.getId())
                .nome(u.getNome())
                .email(u.getEmail())
                .cpf(u.getCpf())
                .build();
    }
}
