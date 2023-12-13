package io.github.nunes03.rests.controllers;

import io.github.nunes03.entities.Cliente;
import io.github.nunes03.rests.controllers.interfaces.ClienteRestControllerInterface;
import io.github.nunes03.services.interfaces.ClienteServiceInterface;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/api/cliente")
@RequiredArgsConstructor
public class ClienteRestController implements ClienteRestControllerInterface {

    private final ClienteServiceInterface clienteServiceInterface;

    public List<Cliente> getAll() {
        return clienteServiceInterface.findAll();
    }

    public Cliente getById(Integer identifier) {
        return clienteServiceInterface.findById(identifier);
    }

    public Cliente postCreated(Cliente entity) {
        return clienteServiceInterface.create(entity);
    }

    public Cliente putUpdated(Cliente entity, Integer identifier) {
        return clienteServiceInterface.update(identifier, entity);
    }

    public void deleteById(Integer identifier) {
        clienteServiceInterface.deleteById(identifier);
    }

    public List<Cliente> getByExample(Cliente cliente) {
        var exampleMatcher = ExampleMatcher
            .matching()
            .withIgnoreCase()
            .withStringMatcher(ExampleMatcher.StringMatcher.CONTAINING);

        var clienteExample = Example.of(cliente, exampleMatcher);
        return clienteServiceInterface.findAll(clienteExample);
    }
}
