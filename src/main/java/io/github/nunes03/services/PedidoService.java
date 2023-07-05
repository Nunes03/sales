package io.github.nunes03.services;

import io.github.nunes03.dto.ItemPedidoDTO;
import io.github.nunes03.dto.PedidoDTO;
import io.github.nunes03.entities.Cliente;
import io.github.nunes03.entities.ItemPedido;
import io.github.nunes03.entities.Pedido;
import io.github.nunes03.entities.Produto;
import io.github.nunes03.repositories.ItemPedidoRepository;
import io.github.nunes03.repositories.PedidoRepository;
import io.github.nunes03.services.interfaces.PedidoServiceInterface;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class PedidoService implements PedidoServiceInterface {

    private final PedidoRepository pedidoRepository;

    private final ItemPedidoRepository itemPedidoRepository;

    public PedidoService(PedidoRepository pedidoRepository, ItemPedidoRepository itemPedidoRepository) {
        this.pedidoRepository = pedidoRepository;
        this.itemPedidoRepository = itemPedidoRepository;
    }

    @Override
    public Pedido save(PedidoDTO pedidoDTO) {
        var pedido = createPedido(pedidoDTO);
        var itensPedido = createItensPedido(pedido, pedidoDTO.getItens());

        pedido.setItensPedido(itensPedido);

        return pedido;
    }


    private Pedido createPedido(PedidoDTO pedidoDTO) {
        var cliente = new Cliente();
        cliente.setId(pedidoDTO.getCliente());

        var pedido = new Pedido();
        pedido.setTotal(pedidoDTO.getTotal());
        pedido.setData(LocalDate.now());
        pedido.setCliente(cliente);

        return pedidoRepository.save(pedido);
    }

    private List<ItemPedido> createItensPedido(Pedido pedido, List<ItemPedidoDTO> itemPedidoDTOS) {
        var itens = new ArrayList<ItemPedido>(itemPedidoDTOS.size());

        itemPedidoDTOS.forEach(
            itemPedidoDTO -> {
                var produto = new Produto();
                produto.setId(itemPedidoDTO.getProduto());

                var itemPedido = new ItemPedido();
                itemPedido.setQuantidade(itemPedidoDTO.getQuantidade());
                itemPedido.setProduto(produto);
                itemPedido.setPedido(pedido);

                itemPedido = itemPedidoRepository.save(itemPedido);
                itens.add(itemPedido);
            }
        );

        return itens;
    }
}
