package io.github.nunes03.rests.controllers.interfaces;

import io.github.nunes03.entities.Cliente;

import java.util.List;

public interface ClienteRestControllerInterface extends RestControllerInterface<Cliente, Integer> {

    List<Cliente> getByExample(Cliente cliente);
}
