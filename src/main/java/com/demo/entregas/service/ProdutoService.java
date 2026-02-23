package com.demo.entregas.service;

import com.demo.entregas.domain.entity.Produto;
import com.demo.entregas.exception.RecursoNaoEncontradoException;
import com.demo.entregas.repository.ProdutoRepository;
import com.demo.entregas.web.dto.produto.ProdutoRequest;
import com.demo.entregas.web.dto.produto.ProdutoResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProdutoService {

    private final ProdutoRepository produtoRepository;

    public List<ProdutoResponse> listarTodos() {
        return produtoRepository.findAll().stream()
                .map(this::toResponse)
                .collect(Collectors.toList());
    }

    public ProdutoResponse buscarPorId(Long id) {
        Produto p = produtoRepository.findById(id)
                .orElseThrow(() -> new RecursoNaoEncontradoException("Produto não encontrado"));
        return toResponse(p);
    }

    @Transactional
    public ProdutoResponse criar(ProdutoRequest request) {
        Produto produto = toEntity(request);
        produto = produtoRepository.save(produto);
        return toResponse(produto);
    }

    @Transactional
    public ProdutoResponse atualizar(Long id, ProdutoRequest request) {
        Produto produto = produtoRepository.findById(id)
                .orElseThrow(() -> new RecursoNaoEncontradoException("Produto não encontrado"));
        atualizarEntity(produto, request);
        produto = produtoRepository.save(produto);
        return toResponse(produto);
    }

    @Transactional
    public void excluir(Long id) {
        if (!produtoRepository.existsById(id)) {
            throw new RecursoNaoEncontradoException("Produto não encontrado");
        }
        produtoRepository.deleteById(id);
    }

    private Produto toEntity(ProdutoRequest r) {
        return Produto.builder()
                .nome(r.getNome())
                .descricao(r.getDescricao())
                .marca(r.getMarca())
                .preco(r.getPreco())
                .estoqueAtual(r.getEstoqueAtual())
                .status(r.getStatus())
                .nomeFornecedor(r.getNomeFornecedor())
                .categoria(r.getCategoria())
                .imagemPrincipalUrl(r.getImagemPrincipalUrl())
                .build();
    }

    private void atualizarEntity(Produto p, ProdutoRequest r) {
        p.setNome(r.getNome());
        p.setDescricao(r.getDescricao());
        p.setMarca(r.getMarca());
        p.setPreco(r.getPreco());
        p.setEstoqueAtual(r.getEstoqueAtual());
        p.setStatus(r.getStatus());
        p.setNomeFornecedor(r.getNomeFornecedor());
        p.setCategoria(r.getCategoria());
        p.setImagemPrincipalUrl(r.getImagemPrincipalUrl());
    }

    private ProdutoResponse toResponse(Produto p) {
        return ProdutoResponse.builder()
                .id(p.getId())
                .nome(p.getNome())
                .descricao(p.getDescricao())
                .marca(p.getMarca())
                .preco(p.getPreco())
                .estoqueAtual(p.getEstoqueAtual())
                .status(p.getStatus())
                .nomeFornecedor(p.getNomeFornecedor())
                .categoria(p.getCategoria())
                .imagemPrincipalUrl(p.getImagemPrincipalUrl())
                .build();
    }
}
