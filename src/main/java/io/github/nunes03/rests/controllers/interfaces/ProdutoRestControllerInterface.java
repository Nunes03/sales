package io.github.nunes03.rests.controllers.interfaces;

import io.github.nunes03.entities.Produto;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

public interface ProdutoRestControllerInterface extends RestControllerInterface<Produto, Integer> {

    @GetMapping(value = "/filter")
    List<Produto> getByExample(Produto produto);
}
