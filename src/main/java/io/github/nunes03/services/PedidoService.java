package io.github.nunes03.services;

import io.github.nunes03.dto.ItemPedidoDTO;
import io.github.nunes03.dto.PedidoDTO;
import io.github.nunes03.entities.Cliente;
import io.github.nunes03.entities.ItemPedido;
import io.github.nunes03.entities.Pedido;
import io.github.nunes03.entities.Produto;
import io.github.nunes03.repositories.ItemPedidoRepository;
import io.github.nunes03.repositories.PedidoRepository;
import io.github.nunes03.services.interfaces.ClienteServiceInterface;
import io.github.nunes03.services.interfaces.PedidoServiceInterface;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PedidoService implements PedidoServiceInterface {

    private final PedidoRepository pedidoRepository;

    private final ClienteServiceInterface clienteServiceInterface;

    private final ItemPedidoRepository itemPedidoRepository;

    @Override
    public Pedido create(PedidoDTO pedidoDTO) {
        var pedido = createPedido(pedidoDTO);
        var itensPedido = createItensPedido(pedido, pedidoDTO.getItens());

        pedido.setItensPedido(itensPedido);

        return pedido;
    }

    @Override
    public List<Pedido> findAll() {
        return null;
    }

    @Override
    public Pedido findById(Integer identifier) {
        return null;
    }

    @Override
    public Pedido create(Pedido entity) {
        return null;
    }

    @Override
    public Pedido update(Integer identifier, Pedido entity) {
        return null;
    }

    @Override
    public void deleteById(Integer identifier) {

    }


    private Pedido createPedido(PedidoDTO pedidoDTO) {
        var pedido = new Pedido();
        pedido.setTotal(pedidoDTO.getTotal());
        pedido.setData(LocalDate.now());

        var cliente = clienteServiceInterface.findById(pedidoDTO.getCliente());
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
