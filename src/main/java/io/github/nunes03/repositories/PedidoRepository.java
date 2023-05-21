package io.github.nunes03.repositories;

import io.github.nunes03.entities.Cliente;
import io.github.nunes03.entities.Pedido;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PedidoRepository extends JpaRepository<Pedido, Integer> {

    List<Pedido> findByCliente(Cliente cliente);
}
