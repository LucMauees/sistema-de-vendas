package com.demo.entregas.service;

import com.demo.entregas.domain.entity.Cliente;
import com.demo.entregas.domain.entity.ItemCarrinho;
import com.demo.entregas.domain.entity.Produto;
import com.demo.entregas.repository.ClienteRepository;
import com.demo.entregas.repository.ItemCarrinhoRepository;
import com.demo.entregas.repository.ProdutoRepository;
import com.demo.entregas.web.dto.ItemCarrinhoRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class CarrinhoService {

    private final ItemCarrinhoRepository itemCarrinhoRepository;
    private final ClienteRepository clienteRepository;
    private final ProdutoRepository produtoRepository;

    public CarrinhoService(ItemCarrinhoRepository itemCarrinhoRepository,
                          ClienteRepository clienteRepository,
                          ProdutoRepository produtoRepository) {
        this.itemCarrinhoRepository = itemCarrinhoRepository;
        this.clienteRepository = clienteRepository;
        this.produtoRepository = produtoRepository;
    }

    @Transactional
    public ItemCarrinho adicionarItem(Long clienteId, ItemCarrinhoRequest request) {
        Cliente cliente = clienteRepository.findById(clienteId)
                .orElseThrow(() -> new IllegalArgumentException("Cliente não encontrado"));

        Produto produto = produtoRepository.findById(request.produtoId())
                .orElseThrow(() -> new IllegalArgumentException("Produto não encontrado"));

        if (produto.getEstoqueAtual() < request.quantidade()) {
            throw new IllegalArgumentException("Quantidade solicitada maior que o estoque disponível");
        }

        // Verifica se o item já existe no carrinho
        Optional<ItemCarrinho> itemExistente = itemCarrinhoRepository
                .findByClienteIdAndProdutoId(clienteId, request.produtoId());

        if (itemExistente.isPresent()) {
            // Atualiza a quantidade do item existente
            ItemCarrinho item = itemExistente.get();
            int novaQuantidade = item.getQuantidade() + request.quantidade();
            
            if (produto.getEstoqueAtual() < novaQuantidade) {
                throw new IllegalArgumentException("Quantidade total solicitada maior que o estoque disponível");
            }
            
            item.setQuantidade(novaQuantidade);
            item.setAtualizadoEm(LocalDateTime.now());
            return itemCarrinhoRepository.save(item);
        } else {
            // Cria um novo item no carrinho
            ItemCarrinho novoItem = new ItemCarrinho(
                    null,
                    cliente,
                    produto,
                    request.quantidade(),
                    LocalDateTime.now(),
                    LocalDateTime.now()
            );
            return itemCarrinhoRepository.save(novoItem);
        }
    }

    @Transactional(readOnly = true)
    public List<ItemCarrinho> listarItens(Long clienteId) {
        if (!clienteRepository.existsById(clienteId)) {
            throw new IllegalArgumentException("Cliente não encontrado");
        }
        return itemCarrinhoRepository.findByClienteId(clienteId);
    }

    @Transactional
    public ItemCarrinho atualizarQuantidade(Long clienteId, Long itemId, Integer novaQuantidade) {
        if (novaQuantidade == null || novaQuantidade <= 0) {
            throw new IllegalArgumentException("Quantidade deve ser maior que zero");
        }

        ItemCarrinho item = itemCarrinhoRepository.findById(itemId)
                .orElseThrow(() -> new IllegalArgumentException("Item do carrinho não encontrado"));

        if (!item.getCliente().getId().equals(clienteId)) {
            throw new IllegalArgumentException("Item não pertence ao cliente informado");
        }

        if (item.getProduto().getEstoqueAtual() < novaQuantidade) {
            throw new IllegalArgumentException("Quantidade solicitada maior que o estoque disponível");
        }

        item.setQuantidade(novaQuantidade);
        item.setAtualizadoEm(LocalDateTime.now());
        return itemCarrinhoRepository.save(item);
    }

    @Transactional
    public void removerItem(Long clienteId, Long itemId) {
        ItemCarrinho item = itemCarrinhoRepository.findById(itemId)
                .orElseThrow(() -> new IllegalArgumentException("Item do carrinho não encontrado"));

        if (!item.getCliente().getId().equals(clienteId)) {
            throw new IllegalArgumentException("Item não pertence ao cliente informado");
        }

        itemCarrinhoRepository.delete(item);
    }
}
