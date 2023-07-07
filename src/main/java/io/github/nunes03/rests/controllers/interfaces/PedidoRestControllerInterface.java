package io.github.nunes03.rests.controllers.interfaces;

import io.github.nunes03.dto.PedidoDTO;
import io.github.nunes03.entities.Pedido;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.List;

public interface PedidoRestControllerInterface extends RestControllerInterface<Pedido, Integer> {

    @PostMapping(value = "/customCreated")
    @ResponseStatus(value = HttpStatus.CREATED)
    Integer save(PedidoDTO pedidoDTO);

    List<Pedido> getByExample(Pedido cliente);
}
