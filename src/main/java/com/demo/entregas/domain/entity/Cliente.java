package com.demo.entregas.domain.entity;

import com.demo.entregas.domain.enumm.StatusCliente;
import com.demo.entregas.domain.enumm.StatusCliente.Status;

import jakarta.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "cliente")
public class Cliente {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @Column(nullable = false)
    private String nomeCompleto;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false, unique = true)
    private String senha;

    @Column(nullable = false)
    private String telefone;

    @Column(nullable = false)
    private String CPF; 

    @Column(nullable = false)
    private LocalDateTime criadoEm;

    @Column(nullable = false)
    private LocalDate AtualizadoEm;

    @Column(nullable = false)
    private StatusCliente.Status status;

    protected Cliente() {}

    public Cliente(Long id, String nomeCompleto, String email, String senha, String telefone, String CPF,
            LocalDateTime criadoEm, LocalDate atualizadoEm, Status status) {
        this.id = id;
        this.nomeCompleto = nomeCompleto;
        this.email = email;
        this.senha = senha;
        this.telefone = telefone;
        this.CPF = CPF;
        this.criadoEm = criadoEm;
        AtualizadoEm = atualizadoEm;
        this.status = status;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNomeCompleto() {
        return nomeCompleto;
    }

    public void setNomeCompleto(String nomeCompleto) {
        this.nomeCompleto = nomeCompleto;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getCPF() {
        return CPF;
    }

    public void setCPF(String cPF) {
        CPF = cPF;
    }

    public LocalDateTime getCriadoEm() {
        return criadoEm;
    }

    public void setCriadoEm(LocalDateTime criadoEm) {
        this.criadoEm = criadoEm;
    }

    public LocalDate getAtualizadoEm() {
        return AtualizadoEm;
    }

    public void setAtualizadoEm(LocalDate atualizadoEm) {
        AtualizadoEm = atualizadoEm;
    }

    public StatusCliente.Status getStatus() {
        return status;
    }

    public void setStatus(StatusCliente.Status status) {
        this.status = status;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    
    
}
