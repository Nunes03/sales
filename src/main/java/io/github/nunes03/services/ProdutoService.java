package io.github.nunes03.services;

import io.github.nunes03.entities.Produto;
import io.github.nunes03.repositories.ProdutoRepository;
import io.github.nunes03.services.interfaces.ProdutoServiceInterface;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Example;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProdutoService implements ProdutoServiceInterface {

    private final ProdutoRepository produtoRepository;

    @Override
    public List<Produto> findByExample(Example<Produto> produtoExample) {
        return produtoRepository.findAll(produtoExample);
    }

    @Override
    public List<Produto> findAll() {
        return produtoRepository.findAll();
    }

    @Override
    public Produto findById(Integer identifier) {
        return produtoRepository.findById(identifier).orElseThrow(
            () -> new ResponseStatusException(
                HttpStatus.BAD_REQUEST,
                "Produto não encontrado."
            )
        );
    }

    @Override
    public Produto create(Produto entity) {
        return produtoRepository.save(entity);
    }

    @Override
    public Produto update(Integer identifier, Produto entity) {
        var produtoFound = findById(identifier);
        entity.setId(produtoFound.getId());

        return produtoRepository.save(entity);
    }

    @Override
    public void deleteById(Integer identifier) {
        var produto = findById(identifier);
        produtoRepository.deleteById(produto.getId());
    }
}
