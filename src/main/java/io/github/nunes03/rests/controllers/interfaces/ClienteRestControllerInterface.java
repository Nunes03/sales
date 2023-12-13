package io.github.nunes03.rests.controllers.interfaces;

import io.github.nunes03.entities.Cliente;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

public interface ClienteRestControllerInterface extends RestControllerInterface<Cliente, Integer> {

    @GetMapping(value = "/filter")
    List<Cliente> getByExample(Cliente cliente);
}
