package com.demo.entregas.service;

import org.springframework.stereotype.Service;

import com.demo.entregas.repository.ClienteRepository;
import com.demo.entregas.web.dto.ClienteRequest;
import com.demo.entregas.domain.entity.Cliente;
import org.springframework.security.crypto.password.PasswordEncoder;
import java.time.LocalDateTime;

import org.springframework.transaction.annotation.Transactional;

@Service
public class ClienteService {

    private final ClienteRepository repository;
    private final PasswordEncoder passwordEncoder;

    public ClienteService(ClienteRepository repository, PasswordEncoder passwordEncoder) {
        this.repository = repository;
        this.passwordEncoder = passwordEncoder;
    }

    @Transactional
    public Cliente cadastrar(ClienteRequest request) {

        if (repository.existsByEmail(request.email())) {
            throw new IllegalArgumentException("Email já cadastrado");
        }
        String hash = passwordEncoder.encode(request.senha());

        Cliente cliente = new Cliente(
                null,
                request.nomeCompleto(),
                request.email(),
                hash,
                request.telefone(),
                request.cpf(),
                LocalDateTime.now(),
                LocalDateTime.now(),
                com.demo.entregas.domain.enumm.StatusCliente.ATIVO

        );
        return repository.save(cliente);

    } 
    
    @Transactional(readOnly = true)
    public Cliente buscarPorId(Long id) {
        return repository.findById(id).orElseThrow(() -> new IllegalArgumentException("Cliente não encontrado"));
    }
    
}
