package com.demo.entregas.service;

import com.demo.entregas.domain.entity.Carrinho;
import com.demo.entregas.domain.entity.ItemCarrinho;
import com.demo.entregas.domain.entity.Produto;
import com.demo.entregas.domain.entity.Usuario;
import com.demo.entregas.exception.RecursoNaoEncontradoException;
import com.demo.entregas.repository.CarrinhoRepository;
import com.demo.entregas.repository.ProdutoRepository;
import com.demo.entregas.repository.UsuarioRepository;
import com.demo.entregas.web.dto.carrinho.CarrinhoResponse;
import com.demo.entregas.web.dto.carrinho.ItemCarrinhoRequest;
import com.demo.entregas.web.dto.carrinho.ItemCarrinhoResponse;
import com.demo.entregas.web.dto.produto.ProdutoResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CarrinhoService {

    private final CarrinhoRepository carrinhoRepository;
    private final UsuarioRepository usuarioRepository;
    private final ProdutoRepository produtoRepository;

    public CarrinhoResponse obterCarrinho(Long usuarioId) {
        Carrinho carrinho = obterOuFalhar(usuarioId);
        return toResponse(carrinho);
    }

    @Transactional
    public CarrinhoResponse adicionarItem(Long usuarioId, ItemCarrinhoRequest request) {
        Carrinho carrinho = obterOuFalhar(usuarioId);
        Produto produto = produtoRepository.findById(request.getProdutoId())
                .orElseThrow(() -> new RecursoNaoEncontradoException("Produto não encontrado"));

        ItemCarrinho existente = carrinho.getItens().stream()
                .filter(i -> i.getProduto().getId().equals(produto.getId()))
                .findFirst()
                .orElse(null);

        if (existente != null) {
            existente.setQuantidade(existente.getQuantidade() + request.getQuantidade());
        } else {
            ItemCarrinho novo = ItemCarrinho.builder()
                    .produto(produto)
                    .quantidade(request.getQuantidade())
                    .build();
            carrinho.adicionarItem(novo);
        }
        carrinho = carrinhoRepository.save(carrinho);
        return toResponse(carrinho);
    }

    @Transactional
    public CarrinhoResponse removerItem(Long usuarioId, Long itemCarrinhoId) {
        Carrinho carrinho = obterOuFalhar(usuarioId);
        ItemCarrinho item = carrinho.getItens().stream()
                .filter(i -> i.getId().equals(itemCarrinhoId))
                .findFirst()
                .orElseThrow(() -> new RecursoNaoEncontradoException("Item não encontrado no carrinho"));
        carrinho.removerItem(item);
        carrinhoRepository.save(carrinho);
        return toResponse(carrinho);
    }

    @Transactional
    public CarrinhoResponse limparCarrinho(Long usuarioId) {
        Carrinho carrinho = obterOuFalhar(usuarioId);
        carrinho.getItens().clear();
        carrinhoRepository.save(carrinho);
        return toResponse(carrinho);
    }

    private Carrinho obterOuFalhar(Long usuarioId) {
        Usuario usuario = usuarioRepository.findById(usuarioId)
                .orElseThrow(() -> new RecursoNaoEncontradoException("Usuário não encontrado"));
        return carrinhoRepository.findByUsuario(usuario)
                .orElseThrow(() -> new RecursoNaoEncontradoException("Carrinho não encontrado"));
    }

    private CarrinhoResponse toResponse(Carrinho c) {
        List<ItemCarrinhoResponse> itens = c.getItens().stream()
                .map(this::toItemResponse)
                .collect(Collectors.toList());
        double total = itens.stream()
                .mapToDouble(ItemCarrinhoResponse::getSubtotal)
                .sum();
        return CarrinhoResponse.builder()
                .id(c.getId())
                .itens(itens)
                .total(total)
                .build();
    }

    private ItemCarrinhoResponse toItemResponse(ItemCarrinho i) {
        Produto p = i.getProduto();
        double subtotal = p.getPreco() * i.getQuantidade();
        ProdutoResponse pr = ProdutoResponse.builder()
                .id(p.getId())
                .nome(p.getNome())
                .preco(p.getPreco())
                .build();
        return ItemCarrinhoResponse.builder()
                .id(i.getId())
                .produto(pr)
                .quantidade(i.getQuantidade())
                .subtotal(subtotal)
                .build();
    }
}
