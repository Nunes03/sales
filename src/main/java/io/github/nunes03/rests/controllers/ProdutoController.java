package io.github.nunes03.rests.controllers;

import io.github.nunes03.entities.Produto;
import io.github.nunes03.repositories.ProdutoRepository;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController()
@RequestMapping(
    value = "/api/produto"
)
public class ProdutoController {

    private final ProdutoRepository produtoRepository;

    public ProdutoController(ProdutoRepository produtoRepository) {
        this.produtoRepository = produtoRepository;
    }

    @GetMapping(
        value = "/{id}"
    )
    public Produto getProduto(@PathVariable("id") Integer id) {
        return produtoRepository
            .findById(id)
            .orElseThrow(
                () -> new ResponseStatusException(
                    HttpStatus.NOT_FOUND,
                    "Produto não encontrado"
                )
            );
    }

    @GetMapping()
    public List<Produto> getProdutos() {
        return produtoRepository.findAll();
    }

    @PostMapping()
    @ResponseStatus(
        value = HttpStatus.CREATED
    )
    public Produto save(@RequestBody Produto produto) {
        return produtoRepository.save(produto);
    }

    @PutMapping(
        value = "/{id}"
    )
    public Produto update(@PathVariable("id") Integer id, @RequestBody Produto produto) {
        var produtoFound = getProduto(id);
        produto.setId(produtoFound.getId());

        return produtoRepository.save(produto);
    }

    @DeleteMapping(
        value = "/{id}"
    )
    @ResponseStatus(
        value = HttpStatus.NO_CONTENT
    )
    public void delete(@PathVariable("id") Integer id) {
        var produto = getProduto(id);
        produtoRepository.deleteById(produto.getId());
    }
}
