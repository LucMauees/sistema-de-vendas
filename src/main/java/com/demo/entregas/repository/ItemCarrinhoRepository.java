package com.demo.entregas.repository;

import com.demo.entregas.domain.entity.ItemCarrinho;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ItemCarrinhoRepository extends JpaRepository<ItemCarrinho, Long> {
}
