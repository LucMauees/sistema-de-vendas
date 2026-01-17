package com.demo.entregas.domain.entity;
import com.demo.entregas.domain.enumm.statusProduto;

import jakarta.persistence.*;

@Entity
@Table(name = "produto")
public class produto {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @Column(nullable = false)
    private String nome;

    @Column(nullable = false)
    private String descricao;

    @Column(nullable = false)
    private String marca;

    @Column(nullable = false)
    private Double preco;

    @Column(nullable = false)
    private Integer estoqueAtual;

    @Column(nullable = false)
    private statusProduto.Status status;

    @Column(nullable = false)
    private String nome_fornecedor;

    @Column(nullable = false)
    private String categoria;

    @Column(nullable = false)
    private String imagemPrincipalurl;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public Double getPreco() {
        return preco;
    }

    public void setPreco(Double preco) {
        this.preco = preco;
    }

    public Integer getEstoqueAtual() {
        return estoqueAtual;
    }

    public void setEstoqueAtual(Integer estoqueAtual) {
        this.estoqueAtual = estoqueAtual;
    }

    public statusProduto.Status getStatus() {
        return status;
    }

    public void setStatus(statusProduto.Status status) {
        this.status = status;
    }

    public String getNome_fornecedor() {
        return nome_fornecedor;
    }

    public void setNome_fornecedor(String nome_fornecedor) {
        this.nome_fornecedor = nome_fornecedor;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public String getImagemPrincipalurl() {
        return imagemPrincipalurl;
    }

    public void setImagemPrincipalurl(String imagemPrincipalurl) {
        this.imagemPrincipalurl = imagemPrincipalurl;
    }



    
}