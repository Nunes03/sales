package io.github.nunes03.rests.controllers.interfaces;

import io.github.nunes03.entities.Produto;

import java.util.List;

public interface ProdutoRestControllerInterface extends RestControllerInterface<Produto, Integer> {

    List<Produto> getByExample(Produto produto);
}
