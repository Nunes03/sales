package io.github.nunes03.rests.controllers.interfaces;

import io.github.nunes03.entities.Cliente;
import io.github.nunes03.entities.ItemPedido;

import java.util.List;

public interface ItemPedidoRestControllerInterface extends RestControllerInterface<ItemPedido, Integer> {

    List<ItemPedido> getByExample(ItemPedido itemPedido);
}
