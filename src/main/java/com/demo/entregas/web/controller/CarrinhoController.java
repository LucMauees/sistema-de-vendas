package com.demo.entregas.web.controller;

import com.demo.entregas.security.CurrentUser;
import com.demo.entregas.service.CarrinhoService;
import com.demo.entregas.web.dto.carrinho.CarrinhoResponse;
import com.demo.entregas.web.dto.carrinho.ItemCarrinhoRequest;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/carrinho")
@RequiredArgsConstructor
@Tag(name = "Carrinho", description = "Carrinho de compras do usuário")
@SecurityRequirement(name = "bearerAuth")
public class CarrinhoController {

    private final CarrinhoService carrinhoService;
    private final CurrentUser currentUser;

    @GetMapping
    @Operation(summary = "Ver carrinho", description = "Retorna itens e total do carrinho do usuário logado")
    public ResponseEntity<CarrinhoResponse> obter() {
        Long usuarioId = currentUser.getUsuarioId();
        return ResponseEntity.ok(carrinhoService.obterCarrinho(usuarioId));
    }

    @PostMapping("/itens")
    @Operation(summary = "Adicionar item ao carrinho")
    public ResponseEntity<CarrinhoResponse> adicionarItem(@Valid @RequestBody ItemCarrinhoRequest request) {
        Long usuarioId = currentUser.getUsuarioId();
        return ResponseEntity.ok(carrinhoService.adicionarItem(usuarioId, request));
    }

    @DeleteMapping("/itens/{itemId}")
    @Operation(summary = "Remover item do carrinho")
    public ResponseEntity<CarrinhoResponse> removerItem(@PathVariable Long itemId) {
        Long usuarioId = currentUser.getUsuarioId();
        return ResponseEntity.ok(carrinhoService.removerItem(usuarioId, itemId));
    }

    @DeleteMapping
    @Operation(summary = "Limpar carrinho")
    public ResponseEntity<CarrinhoResponse> limpar() {
        Long usuarioId = currentUser.getUsuarioId();
        return ResponseEntity.ok(carrinhoService.limparCarrinho(usuarioId));
    }
}
