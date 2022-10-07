package com.grupo2.desafiospring.service;

import com.grupo2.desafiospring.dto.ClientDto;
import com.grupo2.desafiospring.dto.ListClientParamsDto;
import com.grupo2.desafiospring.dto.RegisterClientDto;
import com.grupo2.desafiospring.model.Client;

import java.util.List;
import java.util.UUID;

public interface ClientService {
    List<ClientDto> addClient(List<RegisterClientDto> clientsDto);
    List<Client> listClients(ListClientParamsDto params);
    ClientDto getClientById(UUID id);
}
