package io.github.nunes03.rests.controllers;

import io.github.nunes03.entities.Pedido;
import io.github.nunes03.repositories.PedidoRepository;
import io.github.nunes03.rests.controllers.interfaces.PedidoRestControllerInterface;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping(value = "api/pedido")
public class PedidoController implements PedidoRestControllerInterface {

    private final PedidoRepository pedidoRepository;

    public PedidoController(PedidoRepository pedidoRepository) {
        this.pedidoRepository = pedidoRepository;
    }

    @Override
    @GetMapping()
    public List<Pedido> getAll() {
        return pedidoRepository.findAll();
    }

    @Override
    @GetMapping(value = "/{id}")
    public Pedido getById(@PathVariable(value = "id") Integer identifier) {
        return pedidoRepository.findById(identifier).orElseThrow(
            () -> new ResponseStatusException(
                HttpStatus.BAD_REQUEST,
                "Pedido não encontrado."
            )
        );
    }

    @Override
    @PostMapping()
    @ResponseStatus(value = HttpStatus.CREATED)
    public Pedido postCreated(@RequestBody Pedido entity) {
        return pedidoRepository.save(entity);
    }

    @Override
    @PutMapping(value = "/{id}")
    public Pedido putUpdated(@RequestBody Pedido entity, @PathVariable(value = "id") Integer identifier) {
        var pedidoFound = getById(identifier);
        entity.setId(pedidoFound.getId());

        return pedidoRepository.save(entity);
    }

    @Override
    @DeleteMapping(value = "{id}")
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void deleteById(@PathVariable("id") Integer identifier) {
        var pedidoFound = getById(identifier);
        pedidoRepository.deleteById(pedidoFound.getId());
    }

    @Override
    @GetMapping(value = "/filter")
    public List<Pedido> getByExample(Pedido pedido) {
        var exampleMatcher = ExampleMatcher
            .matching()
            .withIgnoreCase()
            .withStringMatcher(ExampleMatcher.StringMatcher.CONTAINING);

        var pedidoExample = Example.of(pedido, exampleMatcher);
        return pedidoRepository.findAll(pedidoExample);
    }
}
