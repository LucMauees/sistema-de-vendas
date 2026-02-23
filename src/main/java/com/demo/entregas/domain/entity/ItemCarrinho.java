package com.demo.entregas.domain.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "item_carrinho")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ItemCarrinho {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "carrinho_id", nullable = false)
    private Carrinho carrinho;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "produto_id", nullable = false)
    private Produto produto;

    @Column(nullable = false)
    private Integer quantidade;
}
