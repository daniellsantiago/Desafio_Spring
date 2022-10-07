package com.grupo2.desafiospring.service;

import com.grupo2.desafiospring.dto.ClientDto;
import com.grupo2.desafiospring.dto.ListClientParamsDto;
import com.grupo2.desafiospring.dto.RegisterClientDto;
import com.grupo2.desafiospring.exception.InternalServerErrorException;
import com.grupo2.desafiospring.exception.NotFoundException;
import com.grupo2.desafiospring.model.Client;
import com.grupo2.desafiospring.repository.ClientRepository;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class ClientServiceImpl implements ClientService{

    private final ClientRepository clientRepository;

    public ClientServiceImpl(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    @Override
    public List<Client> listClients(ListClientParamsDto params) {
        try {
            return clientRepository.getAllClients(params);
        } catch (Exception e){
            throw new NotFoundException("Cliente não encontrado");
        }
    }

    @Override
    public List<ClientDto> addClient(List<RegisterClientDto> clientsDto) {
        List<Client> clients = clientsDto.stream()
                .map(dto -> dto.toClient(UUID.randomUUID()))
                .collect(Collectors.toList());
        try {
            return ClientDto.fromClientList(clientRepository.addClientRepository(clients));
        } catch(IOException e) {
            throw new InternalServerErrorException("Erro ao salvar o Client");
        }
    }

    @Override
    public ClientDto getClientById(UUID id) {
        try {
            Client client = clientRepository.getClientById(id)
                    .orElseThrow(() -> new NotFoundException("Cliente não encontrado"));
            return ClientDto.toClientDto(client);
        } catch (IOException exception) {
            throw new InternalServerErrorException("Erro ao ler Client");
        }
    }
}
