package io.github.nunes03.rests.controllers;

import io.github.nunes03.entities.Cliente;
import io.github.nunes03.repositories.ClienteRepository;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping(
    value = "/api/clientes"
)
public class ClienteController {

    private final ClienteRepository clienteRepository;

    public ClienteController(ClienteRepository clienteRepository) {
        this.clienteRepository = clienteRepository;
    }

    @GetMapping
    public List<Cliente> getClientes() {
        return clienteRepository.findAll();
    }

    @GetMapping(
        "/{id}"
    )
    public Cliente getClienteById(@PathVariable("id") Integer id) {
        var clienteOptional = clienteRepository.findById(id);

        return clienteOptional.orElseThrow(
            () -> new ResponseStatusException(
                HttpStatus.NOT_FOUND,
                "Cliente não encontrado"
            )
        );
    }

    @PostMapping
    @ResponseStatus(
        value = HttpStatus.CREATED
    )
    public Cliente save(@RequestBody Cliente cliente) {
        return clienteRepository.save(cliente);
    }

    @PutMapping(
        value = "/{id}"
    )
    public Cliente update(@RequestBody Cliente cliente, @PathVariable("id") Integer id) {
        var clienteOptional = clienteRepository.findById(id);

        return clienteOptional
            .map(
                clienteFound -> {
                    cliente.setId(clienteFound.getId());
                    return clienteRepository.save(cliente);
                }
            )
            .orElseThrow(
                () -> new ResponseStatusException(
                    HttpStatus.NOT_FOUND,
                    "Cliente não encontrado"
                )
            );
    }

    @DeleteMapping(
        value = "/{id}"
    )
    @ResponseStatus(
        value = HttpStatus.NO_CONTENT
    )
    public void delete(@PathVariable("id") Integer id) {
        var clienteOptional = clienteRepository.findById(id);

        clienteOptional.ifPresentOrElse(
            cliente -> clienteRepository.deleteById(id),
            () -> {
                throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND,
                    "Cliente não encontrado"
                );
            }
        );
    }

    @GetMapping(
        value = "/filter"
    )
    public List<Cliente> find(Cliente cliente) {
        var exampleMatcher = ExampleMatcher
            .matching()
            .withIgnoreCase()
            .withStringMatcher(ExampleMatcher.StringMatcher.CONTAINING);

        var clienteExample = Example.of(cliente, exampleMatcher);
        return clienteRepository.findAll(clienteExample);
    }
}
