package io.github.nunes03.services;

import io.github.nunes03.entities.ItemPedido;
import io.github.nunes03.repositories.ItemPedidoRepository;
import io.github.nunes03.services.interfaces.ItemPedidoServiceInterface;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ItemPedidoService implements ItemPedidoServiceInterface {

    private final ItemPedidoRepository itemPedidoRepository;

    @Override
    public List<ItemPedido> findAll() {
        return null;
    }

    @Override
    public ItemPedido findById(Integer identifier) {
        return null;
    }

    @Override
    public ItemPedido create(ItemPedido entity) {
        return itemPedidoRepository.save(entity);
    }

    @Override
    public ItemPedido update(Integer identifier, ItemPedido entity) {
        return null;
    }

    @Override
    public void deleteById(Integer identifier) {

    }
}
