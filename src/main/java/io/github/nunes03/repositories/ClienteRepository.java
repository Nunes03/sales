package io.github.nunes03.repositories;

import io.github.nunes03.entities.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface ClienteRepository extends JpaRepository<Cliente, Integer> {

    @Query(
        "select cliente.nome from Cliente cliente " +
            "where " +
            "cliente.nome like :nome"
    )
    List<Cliente> encontrarPorNome(@Param("nome") String nome);

    Cliente findFirstByNome(String nome);

    Boolean existsByNome(String nome);

    @Modifying
    void deleteByNome(String nome);

    @Transactional(readOnly = true)
    @Query(
        "select cliente from Cliente cliente " +
            "left join fetch cliente.pedidos " +
            "where cliente.id = :id"
    )
    Cliente findClienteFetchPedidos(@Param("id") Integer id);
}