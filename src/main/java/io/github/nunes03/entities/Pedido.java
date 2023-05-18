package io.github.nunes03.entities;

import java.math.BigDecimal;
import java.time.LocalDate;

public class Pedido {

    private Integer id;

    private LocalDate data;

    private BigDecimal total;

    private Cliente cliente;

    public Pedido() {
    }

    public Pedido(Integer id, LocalDate data, BigDecimal total, Cliente cliente) {
        this.id = id;
        this.data = data;
        this.total = total;
        this.cliente = cliente;
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
}
