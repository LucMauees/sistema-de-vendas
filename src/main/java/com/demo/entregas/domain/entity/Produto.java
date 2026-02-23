package com.demo.entregas.domain.entity;

import com.demo.entregas.domain.enumm.statusProduto;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "produto")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Produto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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
    @Enumerated(EnumType.STRING)
    private statusProduto.Status status;

    @Column(nullable = false, name = "nome_fornecedor")
    private String nomeFornecedor;

    @Column(nullable = false)
    private String categoria;

    @Column(nullable = false, name = "imagemPrincipalurl")
    private String imagemPrincipalUrl;
}
