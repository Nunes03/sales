package io.github.nunes03.entities;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "pedido")
public class Pedido {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Integer id;

    @Column(
        name = "data"
    )
    private LocalDate data;

    @Column(
        name = "total",
        precision = 20,
        scale = 2
    )
    private BigDecimal total;

    @ManyToOne
    @JoinColumn(
        name = "cliente_id"
    )
    private Cliente cliente;

    @OneToMany(
        mappedBy = "pedido",
        fetch = FetchType.LAZY
    )
    private List<ItemPedido> itensPedido;

    public Pedido() {
    }

    public Pedido(Integer id, LocalDate data, BigDecimal total, Cliente cliente, List<ItemPedido> itensPedido) {
        this.id = id;
        this.data = data;
        this.total = total;
        this.cliente = cliente;
        this.itensPedido = itensPedido;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public LocalDate getData() {
        return data;
    }

    public void setData(LocalDate data) {
        this.data = data;
    }

    public BigDecimal getTotal() {
        return total;
    }

    public void setTotal(BigDecimal total) {
        this.total = total;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public List<ItemPedido> getItensPedido() {
        return itensPedido;
    }

    public void setItensPedido(List<ItemPedido> itensPedido) {
        this.itensPedido = itensPedido;
    }

    @Override
    public String toString() {
        return "Pedido{" +
            "id=" + id +
            ", data=" + data +
            ", total=" + total +
            ", cliente=" + cliente.getNome() +
            //", itensPedido=" + itensPedido +
            '}';
    }
}
