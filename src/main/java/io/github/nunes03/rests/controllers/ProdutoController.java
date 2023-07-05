package io.github.nunes03.rests.controllers;

import io.github.nunes03.entities.Produto;
import io.github.nunes03.repositories.ProdutoRepository;
import io.github.nunes03.rests.controllers.interfaces.ProdutoRestControllerInterface;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController()
@RequestMapping(value = "/api/produto")
public class ProdutoController implements ProdutoRestControllerInterface {

    private final ProdutoRepository produtoRepository;

    public ProdutoController(ProdutoRepository produtoRepository) {
        this.produtoRepository = produtoRepository;
    }

    @Override
    @GetMapping()
    public List<Produto> getAll() {
        return produtoRepository.findAll();
    }

    @Override
    @GetMapping(value = "{id}")
    public Produto getById(@PathVariable("id") Integer identifier) {
        return produtoRepository.findById(identifier).orElseThrow(
            () -> new ResponseStatusException(
                HttpStatus.BAD_REQUEST,
                "Produto não encontrado."
            )
        );
    }

    @Override
    @PostMapping()
    @ResponseStatus(value = HttpStatus.CREATED)
    public Produto postCreated(@RequestBody Produto entity) {
        return produtoRepository.save(entity);
    }

    @Override
    @PutMapping(value = "/{id}")
    public Produto putUpdated(@RequestBody Produto entity, @PathVariable(value = "id") Integer identifier) {
        var produtoFound = getById(identifier);
        entity.setId(produtoFound.getId());

        return produtoRepository.save(entity);
    }

    @Override
    @DeleteMapping(value = "/{id}")
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void deleteById(Integer identifier) {
        var produtoFound = getById(identifier);
        produtoRepository.deleteById(produtoFound.getId());
    }

    @Override
    @GetMapping(value = "/filter")
    public List<Produto> getByExample(Produto produto) {
        var example = ExampleMatcher
            .matching()
            .withIgnoreCase()
            .withStringMatcher(ExampleMatcher.StringMatcher.CONTAINING);

        var produtoExample = Example.of(produto, example);
        return produtoRepository.findAll(produtoExample);
    }
}
