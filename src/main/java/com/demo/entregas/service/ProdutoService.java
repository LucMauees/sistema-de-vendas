package com.demo.entregas.service;

import org.springframework.stereotype.Service;
import com.demo.entregas.repository.ProdutoRepository;

import jakarta.transaction.Transactional;

import com.demo.entregas.domain.entity.Produto;

@Service
public class ProdutoService {

    private final ProdutoRepository repository;

    public ProdutoService(ProdutoRepository repository) {
        this.repository = repository;
    }
    
    @Transactional
    public Produto buscaProdutoId(Long id) {
        return repository.findById(id).orElseThrow(() -> new IllegalArgumentException("Produto não encontrado"));
    }
}
