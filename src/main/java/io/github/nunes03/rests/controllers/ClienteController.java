package io.github.nunes03.rests.controllers;

import io.github.nunes03.entities.Cliente;
import io.github.nunes03.rests.controllers.interfaces.ClienteRestControllerInterface;
import io.github.nunes03.services.interfaces.ClienteServiceInterface;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/api/clientes")
@RequiredArgsConstructor
public class ClienteController implements ClienteRestControllerInterface {

    private final ClienteServiceInterface clienteServiceInterface;

    @Override
    @GetMapping()
    public List<Cliente> getAll() {
        return clienteServiceInterface.findAll();
    }

    @Override
    @GetMapping(value = "/{id}")
    public Cliente getById(@PathVariable(value = "id") Integer identifier) {
        return clienteServiceInterface.findById(identifier);
    }

    @Override
    @PostMapping()
    @ResponseStatus(value = HttpStatus.CREATED)
    public Cliente postCreated(@RequestBody Cliente entity) {
        return clienteServiceInterface.create(entity);
    }

    @Override
    @PutMapping(value = "/{id}")
    public Cliente putUpdated(@RequestBody Cliente entity, @PathVariable("id") Integer identifier) {
        return clienteServiceInterface.update(identifier, entity);
    }

    @DeleteMapping(value = "/{id}")
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void deleteById(@PathVariable("id") Integer identifier) {
        clienteServiceInterface.deleteById(identifier);
    }

    @Override
    @GetMapping(value = "/filter")
    public List<Cliente> getByExample(Cliente cliente) {
        return clienteServiceInterface.findAll(cliente);
    }
}
