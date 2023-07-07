package io.github.nunes03.rests.controllers;

import io.github.nunes03.dto.PedidoDTO;
import io.github.nunes03.entities.Pedido;
import io.github.nunes03.rests.controllers.interfaces.PedidoRestControllerInterface;
import io.github.nunes03.services.interfaces.PedidoServiceInterface;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/api/pedido")
public class PedidoRestController implements PedidoRestControllerInterface {

    private final PedidoServiceInterface pedidoServiceInterface;

    public PedidoRestController(PedidoServiceInterface pedidoServiceInterface) {
        this.pedidoServiceInterface = pedidoServiceInterface;
    }

    public Integer save(@RequestBody PedidoDTO pedidoDTO) {
        var pedido = pedidoServiceInterface.create(pedidoDTO);

        return pedido.getId();
    }

    @Override
    public List<Pedido> getByExample(Pedido cliente) {
        return null;
    }

    @Override
    public List<Pedido> getAll() {
        return pedidoServiceInterface.findAll();
    }

    @Override
    public Pedido getById(Integer identifier) {
        return null;
    }

    @Override
    public Pedido postCreated(Pedido entity) {
        return null;
    }

    @Override
    public Pedido putUpdated(Pedido entity, Integer identifier) {
        return null;
    }

    @Override
    public void deleteById(Integer identifier) {

    }
}
