package io.github.nunes03.rests.controllers.interfaces;

import io.github.nunes03.entities.Pedido;

import java.util.List;

public interface PedidoRestControllerInterface extends RestControllerInterface<Pedido, Integer> {

    List<Pedido> getByExample(Pedido cliente);
}
