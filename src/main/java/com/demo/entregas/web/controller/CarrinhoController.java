package com.demo.entregas.web.controller;

import com.demo.entregas.domain.entity.ItemCarrinho;
import com.demo.entregas.service.CarrinhoService;
import com.demo.entregas.web.dto.AtualizarQuantidadeRequest;
import com.demo.entregas.web.dto.ItemCarrinhoRequest;
import com.demo.entregas.web.dto.ItemCarrinhoResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

@Tag(name = "Carrinho Controller", description = "Endpoints para gerenciamento do carrinho de compras")
@RequestMapping("/carrinho")
@RestController
public class CarrinhoController {

    private final CarrinhoService carrinhoService;

    public CarrinhoController(CarrinhoService carrinhoService) {
        this.carrinhoService = carrinhoService;
    }

    @Operation(summary = "Adicionar item ao carrinho", 
               description = "Adiciona um produto ao carrinho do cliente. Se o produto já existir, incrementa a quantidade.")
    @PostMapping("/{clienteId}/itens")
    public ResponseEntity<ItemCarrinhoResponse> adicionarItem(
            @PathVariable Long clienteId,
            @RequestBody @Valid ItemCarrinhoRequest request) {
        
        ItemCarrinho item = carrinhoService.adicionarItem(clienteId, request);
        
        ItemCarrinhoResponse response = new ItemCarrinhoResponse(
                item.getId(),
                item.getProduto().getId(),
                item.getProduto().getNome(),
                item.getQuantidade(),
                item.getProduto().getPreco(),
                item.getProduto().getPreco() * item.getQuantidade()
        );
        
        URI location = URI.create("/carrinho/" + clienteId + "/itens/" + item.getId());
        return ResponseEntity.created(location).body(response);
    }

    @Operation(summary = "Listar itens do carrinho", 
               description = "Retorna todos os itens do carrinho do cliente com nome e quantidade")
    @GetMapping("/{clienteId}/itens")
    public ResponseEntity<List<ItemCarrinhoResponse>> listarItens(@PathVariable Long clienteId) {
        List<ItemCarrinho> itens = carrinhoService.listarItens(clienteId);
        
        List<ItemCarrinhoResponse> response = itens.stream()
                .map(item -> new ItemCarrinhoResponse(
                        item.getId(),
                        item.getProduto().getId(),
                        item.getProduto().getNome(),
                        item.getQuantidade(),
                        item.getProduto().getPreco(),
                        item.getProduto().getPreco() * item.getQuantidade()
                ))
                .collect(Collectors.toList());
        
        return ResponseEntity.ok(response);
    }

    @Operation(summary = "Atualizar quantidade do item", 
               description = "Atualiza a quantidade de um item específico no carrinho")
    @PutMapping("/{clienteId}/itens/{itemId}")
    public ResponseEntity<ItemCarrinhoResponse> atualizarQuantidade(
            @PathVariable Long clienteId,
            @PathVariable Long itemId,
            @RequestBody @Valid AtualizarQuantidadeRequest request) {
        
        ItemCarrinho item = carrinhoService.atualizarQuantidade(clienteId, itemId, request.quantidade());
        
        ItemCarrinhoResponse response = new ItemCarrinhoResponse(
                item.getId(),
                item.getProduto().getId(),
                item.getProduto().getNome(),
                item.getQuantidade(),
                item.getProduto().getPreco(),
                item.getProduto().getPreco() * item.getQuantidade()
        );
        
        return ResponseEntity.ok(response);
    }

    @Operation(summary = "Remover item do carrinho", 
               description = "Remove um item específico do carrinho do cliente")
    @DeleteMapping("/{clienteId}/itens/{itemId}")
    public ResponseEntity<Void> removerItem(
            @PathVariable Long clienteId,
            @PathVariable Long itemId) {
        
        carrinhoService.removerItem(clienteId, itemId);
        return ResponseEntity.noContent().build();
    }
}
