package io.github.nunes03.entities;

import javax.persistence.*;

@Entity
@Table(
    name = "item_pedido",
    uniqueConstraints = {
        @UniqueConstraint(
            columnNames = {
                "pedido_id",
                "produto_id"
            }
        )
    }
)
public class ItemPedido {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Integer id;

    @Column(
        name = "quantidade"
    )
    private Integer quantidade;

    @ManyToOne
    @JoinColumn(
        name = "pedido_id"
    )
    private Pedido pedido;

    @ManyToOne
    @JoinColumn(
        name = "produto_id"
    )
    private Produto produto;

    public ItemPedido() {
    }

    public ItemPedido(Integer id, Integer quantidade, Pedido pedido, Produto produto) {
        this.id = id;
        this.quantidade = quantidade;
        this.pedido = pedido;
        this.produto = produto;
    }
}
