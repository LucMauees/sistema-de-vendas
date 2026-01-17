package com.demo.entregas.repository;
import com.demo.entregas.domain.entity.cliente;
import org.springframework.data.jpa.repository.JpaRepository;

public interface clienteRepository extends JpaRepository<cliente, Long> {

}
