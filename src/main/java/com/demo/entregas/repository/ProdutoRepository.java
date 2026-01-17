package com.demo.entregas.repository;
import com.demo.entregas.domain.entity.produto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface produtoRepository extends JpaRepository<produto, Long> {

}
