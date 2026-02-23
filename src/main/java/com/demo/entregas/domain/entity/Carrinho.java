package com.demo.entregas.domain.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "carrinho")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Carrinho {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "usuario_id", nullable = false, unique = true)
    private Usuario usuario;

    @OneToMany(mappedBy = "carrinho", cascade = CascadeType.ALL, orphanRemoval = true)
    @Builder.Default
    private List<ItemCarrinho> itens = new ArrayList<>();

    public void adicionarItem(ItemCarrinho item) {
        itens.add(item);
        item.setCarrinho(this);
    }

    public void removerItem(ItemCarrinho item) {
        itens.remove(item);
        item.setCarrinho(null);
    }
}
