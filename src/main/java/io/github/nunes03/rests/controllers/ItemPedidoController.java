package io.github.nunes03.rests.controllers;

import io.github.nunes03.entities.ItemPedido;
import io.github.nunes03.repositories.ItemPedidoRepository;
import io.github.nunes03.rests.controllers.interfaces.ItemPedidoRestControllerInterface;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping(value = "/api/clientes")
public class ItemPedidoController implements ItemPedidoRestControllerInterface {

    private final ItemPedidoRepository itemPedidoRepository;

    public ItemPedidoController(ItemPedidoRepository itemPedidoRepository) {
        this.itemPedidoRepository = itemPedidoRepository;
    }

    @Override
    @GetMapping()
    public List<ItemPedido> getAll() {
        return itemPedidoRepository.findAll();
    }

    @Override
    @GetMapping(value = "/{id}")
    public ItemPedido getById(@PathVariable(value = "id") Integer identifier) {
        var clienteOptional = itemPedidoRepository.findById(identifier);

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
    public ItemPedido postCreated(@RequestBody ItemPedido entity) {
        return itemPedidoRepository.save(entity);
    }

    @Override
    @PutMapping(value = "/{id}")
    public ItemPedido putUpdated(@RequestBody ItemPedido entity, @PathVariable("id") Integer identifier) {
        var clienteOptional = getById(identifier);
        entity.setId(clienteOptional.getId());

        return itemPedidoRepository.save(entity);
    }

    @DeleteMapping(value = "/{id}")
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void deleteById(@PathVariable("id") Integer identifier) {
        itemPedidoRepository.deleteById(
            getById(identifier).getId()
        );
    }

    @Override
    @GetMapping(value = "/filter")
    public List<ItemPedido> getByExample(ItemPedido itemPedido) {
        var exampleMatcher = ExampleMatcher
            .matching()
            .withIgnoreCase()
            .withStringMatcher(ExampleMatcher.StringMatcher.CONTAINING);

        var clienteExample = Example.of(itemPedido, exampleMatcher);
        return itemPedidoRepository.findAll(clienteExample);
    }
}
