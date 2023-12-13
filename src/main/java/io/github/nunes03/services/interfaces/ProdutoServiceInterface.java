package io.github.nunes03.services.interfaces;

import io.github.nunes03.entities.Produto;
import org.springframework.data.domain.Example;

import java.util.List;

public interface ProdutoServiceInterface extends ServiceInterface<Produto, Integer> {

    List<Produto> findByExample(Example<Produto> produtoExample);
}
