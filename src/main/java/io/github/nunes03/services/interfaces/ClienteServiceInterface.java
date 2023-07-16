package io.github.nunes03.services.interfaces;

import io.github.nunes03.entities.Cliente;
import org.springframework.data.domain.Example;

import java.util.List;

public interface ClienteServiceInterface extends ServiceInterface<Cliente, Integer> {

    List<Cliente> findAll(Example<Cliente> clienteExample);
}
