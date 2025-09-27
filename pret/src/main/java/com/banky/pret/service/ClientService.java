package com.banky.pret.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.banky.pret.repository.ClientRepository;
import com.banky.pret.model.Client;


@Service
public class ClientService  {
    private final ClientRepository clientRepository;

    public ClientService(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

 
    public Client addClient(Client client) {
        return clientRepository.save(client);
    }

    public List<Client> getAllClient (){
        return clientRepository.findAll();
    }

    public Client updateClient (Client client) {
        return clientRepository.save(client);
    }

    public void deleteById(Long id) {
        clientRepository.deleteById(id);
    }
}
