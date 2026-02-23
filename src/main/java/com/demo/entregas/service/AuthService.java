package com.demo.entregas.service;

import com.demo.entregas.domain.entity.Carrinho;
import com.demo.entregas.domain.entity.Usuario;
import com.demo.entregas.exception.CredenciaisInvalidasException;
import com.demo.entregas.exception.UsuarioJaExisteException;
import com.demo.entregas.repository.CarrinhoRepository;
import com.demo.entregas.repository.UsuarioRepository;
import com.demo.entregas.security.JwtService;
import com.demo.entregas.web.dto.auth.LoginRequest;
import com.demo.entregas.web.dto.auth.LoginResponse;
import com.demo.entregas.web.dto.auth.RegistrarRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UsuarioRepository usuarioRepository;
    private final CarrinhoRepository carrinhoRepository;
    private final JwtService jwtService;
    private final PasswordEncoder passwordEncoder;

    @Transactional
    public LoginResponse registrar(RegistrarRequest request) {
        if (usuarioRepository.existsByEmail(request.getEmail())) {
            throw new UsuarioJaExisteException("Já existe usuário com este email");
        }
        if (usuarioRepository.existsByCpf(normalizarCpf(request.getCpf()))) {
            throw new UsuarioJaExisteException("Já existe usuário com este CPF");
        }
        Usuario usuario = Usuario.builder()
                .nome(request.getNome())
                .email(request.getEmail())
                .senha(passwordEncoder.encode(request.getSenha()))
                .cpf(normalizarCpf(request.getCpf()))
                .build();
        usuario = usuarioRepository.save(usuario);
        Carrinho carrinho = Carrinho.builder().usuario(usuario).build();
        carrinhoRepository.save(carrinho);

        String token = jwtService.gerarToken(usuario.getEmail(), usuario.getId());
        return LoginResponse.of(token, usuario.getId(), usuario.getEmail());
    }

    public LoginResponse login(LoginRequest request) {
        Usuario usuario = usuarioRepository.findByEmail(request.getEmail())
                .orElseThrow(CredenciaisInvalidasException::new);
        if (!passwordEncoder.matches(request.getSenha(), usuario.getSenha())) {
            throw new CredenciaisInvalidasException();
        }
        String token = jwtService.gerarToken(usuario.getEmail(), usuario.getId());
        return LoginResponse.of(token, usuario.getId(), usuario.getEmail());
    }

    private static String normalizarCpf(String cpf) {
        return cpf == null ? "" : cpf.replaceAll("\\D", "");
    }
}
