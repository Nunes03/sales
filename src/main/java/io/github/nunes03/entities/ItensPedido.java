package io.github.nunes03.entities;

public class ItensPedido {

    private Integer id;

    private Integer quantidade;

    private Pedido pedido;

    private Produto produto;

    public ItensPedido() {
    }

    public ItensPedido(Integer id, Integer quantidade, Pedido pedido, Produto produto) {
        this.id = id;
        this.quantidade = quantidade;
        this.pedido = pedido;
        this.produto = produto;
    }
}
