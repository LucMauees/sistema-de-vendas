package com.demo.entregas.repository;
import com.demo.entregas.domain.entity.Produto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProdutoRepository extends JpaRepository<Produto, Long> {

}
