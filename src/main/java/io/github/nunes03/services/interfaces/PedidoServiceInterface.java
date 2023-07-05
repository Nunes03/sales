package io.github.nunes03.services.interfaces;

import io.github.nunes03.dto.PedidoDTO;
import io.github.nunes03.entities.Pedido;

public interface PedidoServiceInterface {

    Pedido save(PedidoDTO pedidoDTO);
}
