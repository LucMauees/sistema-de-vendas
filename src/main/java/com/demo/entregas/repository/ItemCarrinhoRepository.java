package com.demo.entregas.repository;

import com.demo.entregas.domain.entity.ItemCarrinho;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import java.util.Optional;

public interface ItemCarrinhoRepository extends JpaRepository<ItemCarrinho, Long> {
    
    List<ItemCarrinho> findByClienteId(Long clienteId);
    
    Optional<ItemCarrinho> findByClienteIdAndProdutoId(Long clienteId, Long produtoId);
    
    void deleteByClienteIdAndProdutoId(Long clienteId, Long produtoId);
    
    void deleteByClienteId(Long clienteId);
}
