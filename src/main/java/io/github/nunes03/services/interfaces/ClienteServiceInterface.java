package io.github.nunes03.services.interfaces;

import io.github.nunes03.entities.Cliente;

import java.util.List;

public interface ClienteServiceInterface extends ServiceInterface<Cliente, Integer> {

    List<Cliente> findAll(Cliente cliente);
}
