package com.demo.entregas.domain.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

import java.time.Instant;

@Entity
@Table(name = "usuario")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Column(nullable = false)
    private String nome;

    @NotBlank
    @Column(nullable = false)
    private String senha;

    @NotBlank
    @Email
    @Column(nullable = false, unique = true)
    private String email;

    @NotBlank
    @Column(nullable = false, unique = true, length = 14)
    private String cpf;

    @Column(nullable = false, updatable = false)
    private Instant criadoEm;

    @PrePersist
    public void prePersist() {
        if (criadoEm == null) criadoEm = Instant.now();
    }
}
