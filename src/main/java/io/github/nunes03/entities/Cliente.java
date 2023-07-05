package io.github.nunes03.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "cliente")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Cliente {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Integer id;

    @Column(
        name = "nome",
        length = 100
    )
    private String nome;

    @Column(
        name = "cpf",
        length = 11
    )
    private String cpf;

    @OneToMany(
        mappedBy = "cliente",
        fetch = FetchType.LAZY
    )
    @JsonIgnore
    private List<Pedido> pedidos = new ArrayList<>();
}
