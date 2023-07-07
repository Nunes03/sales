package io.github.nunes03.rests.controllers;

import io.github.nunes03.entities.Produto;
import io.github.nunes03.rests.controllers.interfaces.ProdutoRestControllerInterface;
import io.github.nunes03.services.interfaces.ProdutoServiceInterface;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController()
@RequestMapping(value = "/api/produto")
public class ProdutoRestController implements ProdutoRestControllerInterface {

    private final ProdutoServiceInterface produtoServiceInterface;

    public ProdutoRestController(ProdutoServiceInterface produtoServiceInterface) {
        this.produtoServiceInterface = produtoServiceInterface;
    }

    @Override
    @GetMapping()
    public List<Produto> getAll() {
        return produtoServiceInterface.findAll();
    }

    @Override
    @GetMapping(value = "{id}")
    public Produto getById(@PathVariable("id") Integer identifier) {
        return produtoServiceInterface.findById(identifier);
    }

    @Override
    @PostMapping()
    @ResponseStatus(value = HttpStatus.CREATED)
    public Produto postCreated(@RequestBody Produto entity) {
        return produtoServiceInterface.create(entity);
    }

    @Override
    @PutMapping(value = "/{id}")
    public Produto putUpdated(@RequestBody Produto entity, @PathVariable(value = "id") Integer identifier) {
        return produtoServiceInterface.update(identifier, entity);
    }

    @Override
    @DeleteMapping(value = "/{id}")
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void deleteById(@PathVariable(value = "id") Integer identifier) {
        produtoServiceInterface.deleteById(identifier);
    }

    @Override
    @GetMapping(value = "/filter")
    public List<Produto> getByExample(Produto produto) {
        var example = ExampleMatcher
            .matching()
            .withIgnoreCase()
            .withStringMatcher(ExampleMatcher.StringMatcher.CONTAINING);

        var produtoExample = Example.of(produto, example);
        return produtoServiceInterface.findByExample(produtoExample);
    }
}
