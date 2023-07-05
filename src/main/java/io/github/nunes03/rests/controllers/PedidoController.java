package io.github.nunes03.rests.controllers;

import io.github.nunes03.dto.PedidoDTO;
import io.github.nunes03.services.interfaces.PedidoServiceInterface;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "api/pedido")
public class PedidoController {

    private final PedidoServiceInterface pedidoServiceInterface;

    public PedidoController(PedidoServiceInterface pedidoServiceInterface) {
        this.pedidoServiceInterface = pedidoServiceInterface;
    }

    @PostMapping()
    @ResponseStatus(value = HttpStatus.CREATED)
    public Integer save(@RequestBody PedidoDTO pedidoDTO) {
        var pedido = pedidoServiceInterface.save(pedidoDTO);

        return pedido.getId();
    }
}
