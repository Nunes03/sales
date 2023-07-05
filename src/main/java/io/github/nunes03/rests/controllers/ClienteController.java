package io.github.nunes03.rests.controllers;

import io.github.nunes03.entities.Cliente;
import io.github.nunes03.repositories.ClienteRepository;
import io.github.nunes03.rests.controllers.interfaces.ClienteRestControllerInterface;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping(value = "/api/clientes")
public class ClienteController implements ClienteRestControllerInterface {

    private final ClienteRepository clienteRepository;

    public ClienteController(ClienteRepository clienteRepository) {
        this.clienteRepository = clienteRepository;
    }

    @Override
    @GetMapping()
    public List<Cliente> getAll() {
        return clienteRepository.findAll();
    }

    @Override
    @GetMapping(value = "/{id}")
    public Cliente getById(@PathVariable(value = "id") Integer identifier) {
        var clienteOptional = clienteRepository.findById(identifier);

        return clienteOptional.orElseThrow(
            () -> new ResponseStatusException(
                HttpStatus.NOT_FOUND,
                "Cliente não encontrado"
            )
        );
    }

    @Override
    @PostMapping()
    @ResponseStatus(value = HttpStatus.CREATED)
    public Cliente postCreated(@RequestBody Cliente entity) {
        return clienteRepository.save(entity);
    }

    @Override
    @PutMapping(value = "/{id}")
    public Cliente putUpdated(@RequestBody Cliente entity, @PathVariable("id") Integer identifier) {
        var clienteOptional = getById(identifier);
        entity.setId(clienteOptional.getId());

        return clienteRepository.save(entity);
    }

    @DeleteMapping(value = "/{id}")
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void deleteById(@PathVariable("id") Integer identifier) {
        clienteRepository.deleteById(
            getById(identifier).getId()
        );
    }

    @Override
    @GetMapping(value = "/filter")
    public List<Cliente> getByExample(Cliente cliente) {
        var exampleMatcher = ExampleMatcher
            .matching()
            .withIgnoreCase()
            .withStringMatcher(ExampleMatcher.StringMatcher.CONTAINING);

        var clienteExample = Example.of(cliente, exampleMatcher);
        return clienteRepository.findAll(clienteExample);
    }
}
