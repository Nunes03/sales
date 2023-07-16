package io.github.nunes03.services;

import io.github.nunes03.entities.Cliente;
import io.github.nunes03.repositories.ClienteRepository;
import io.github.nunes03.services.interfaces.ClienteServiceInterface;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Example;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ClienteService implements ClienteServiceInterface {

    private final ClienteRepository clienteRepository;

    @Override
    public List<Cliente> findAll() {
        return clienteRepository.findAll();
    }

    @Override
    public Cliente findById(Integer identifier) {
        return clienteRepository.findById(identifier).orElseThrow(
            () -> new ResponseStatusException(
                HttpStatus.NOT_FOUND,
                "Cliente não encontrado"
            )
        );
    }

    @Override
    public Cliente create(Cliente entity) {
        return clienteRepository.save(entity);
    }

    @Override
    public Cliente update(Integer identifier, Cliente entity) {
        var cliente = findById(entity.getId());
        entity.setId(cliente.getId());

        return clienteRepository.save(entity);
    }

    @Override
    public void deleteById(Integer identifier) {
        var cliente = findById(identifier);
        clienteRepository.deleteById(cliente.getId());
    }

    @Override
    public List<Cliente> findAll(Example<Cliente> clienteExample) {
        return clienteRepository.findAll(clienteExample);
    }
}
