package io.github.nunes03.rests.controllers;

import io.github.nunes03.entities.Produto;
import io.github.nunes03.rests.controllers.interfaces.ProdutoRestControllerInterface;
import io.github.nunes03.services.interfaces.ProdutoServiceInterface;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController()
@RequestMapping(value = "/api/produto")
public class ProdutoRestController implements ProdutoRestControllerInterface {

    private final ProdutoServiceInterface produtoServiceInterface;

    public ProdutoRestController(ProdutoServiceInterface produtoServiceInterface) {
        this.produtoServiceInterface = produtoServiceInterface;
    }

    @Override
    public List<Produto> getAll() {
        return produtoServiceInterface.findAll();
    }

    @Override
    public Produto getById(@PathVariable("id") Integer identifier) {
        return produtoServiceInterface.findById(identifier);
    }

    @Override
    public Produto postCreated(@RequestBody Produto entity) {
        return produtoServiceInterface.create(entity);
    }

    @Override
    public Produto putUpdated(@RequestBody Produto entity, @PathVariable(value = "id") Integer identifier) {
        return produtoServiceInterface.update(identifier, entity);
    }

    @Override
    public void deleteById(@PathVariable(value = "id") Integer identifier) {
        produtoServiceInterface.deleteById(identifier);
    }

    @Override
    public List<Produto> getByExample(Produto produto) {
        var example = ExampleMatcher
                .matching()
                .withIgnoreCase()
                .withStringMatcher(ExampleMatcher.StringMatcher.CONTAINING);

        var produtoExample = Example.of(produto, example);
        return produtoServiceInterface.findByExample(produtoExample);
    }
}
