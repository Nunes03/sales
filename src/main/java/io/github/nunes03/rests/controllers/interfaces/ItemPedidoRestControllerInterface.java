package io.github.nunes03.rests.controllers.interfaces;

import io.github.nunes03.entities.ItemPedido;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

public interface ItemPedidoRestControllerInterface extends RestControllerInterface<ItemPedido, Integer> {

    @GetMapping(value = "/filter")
    List<ItemPedido> getByExample(ItemPedido itemPedido);
}
