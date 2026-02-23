package com.demo.entregas.repository;

import com.demo.entregas.domain.entity.Carrinho;
import com.demo.entregas.domain.entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CarrinhoRepository extends JpaRepository<Carrinho, Long> {

    Optional<Carrinho> findByUsuario(Usuario usuario);

    Optional<Carrinho> findByUsuarioId(Long usuarioId);
}
