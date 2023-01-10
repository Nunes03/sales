package io.github.nunes03.services;

import io.github.nunes03.models.Client;
import io.github.nunes03.repositories.ClientRepository;
import org.springframework.stereotype.Service;

@Service
public class ClientService {

    private final ClientRepository clientRepository;

    public ClientService(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    public void save(Client client) {
        validate(client);
        clientRepository.save(client);
    }

    public void validate(Client client) {
    }
}
