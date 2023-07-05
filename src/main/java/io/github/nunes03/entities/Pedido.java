package io.github.nunes03.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "pedido")
@AllArgsConstructor
@NoArgsConstructor
@Data
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
}
