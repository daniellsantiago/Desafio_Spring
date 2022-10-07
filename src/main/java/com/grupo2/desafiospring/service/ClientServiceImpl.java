package com.grupo2.desafiospring.service;

import com.grupo2.desafiospring.dto.ClientDto;
import com.grupo2.desafiospring.dto.ListClientParamsDto;
import com.grupo2.desafiospring.exception.InternalServerErrorException;
import com.grupo2.desafiospring.exception.NotFoundException;
import com.grupo2.desafiospring.model.Client;
import com.grupo2.desafiospring.repository.ClientRepository;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;
import java.util.Objects;

@Service
public class ClientServiceImpl implements ClientService{

    private final ClientRepository clientRepository;

    public ClientServiceImpl(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    @Override
    public List<Client> listClients(ListClientParamsDto params) {
        try {
            List<Client> clients = clientRepository.getAllClients(params);
            return clients;
        } catch (Exception e){
            throw new NotFoundException("Cliente não encontrado");
        }
    }

    @Override
    public List<ClientDto> addClient(List<Client> client) {
        try{
            return ClientDto.convertDto(clientRepository.addClientRepository(client));
        }catch(IOException e){
            throw new InternalServerErrorException("Error trying to write products");
        }
    }

    @Override
    public Client getClientById(Long id) {
        Client client = clientRepository.getClientById(id);
        try {
            client.getId();
        }catch (Exception e) {
            throw new NotFoundException("Cliente não encontrado");
        }
        return client;
    }

    private boolean validateClient(Client client) {
        if (Objects.isNull(client.getId())) return false;
        if (Objects.isNull(client.getName())) return false;
        if (Objects.isNull(client.getCpf())) return false;
        if (Objects.isNull(client.getState())) return false;
        return true;
    }
}
