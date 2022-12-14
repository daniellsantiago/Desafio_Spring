package com.grupo2.desafiospring.service;

import com.grupo2.desafiospring.dto.ClientDto;
import com.grupo2.desafiospring.dto.ListClientParamsDto;
import com.grupo2.desafiospring.dto.RegisterClientDto;

import java.util.List;
import java.util.UUID;

public interface ClientService {
    List<ClientDto> addClient(List<RegisterClientDto> clientsDto);
    List<ClientDto> listClients(ListClientParamsDto params);
    ClientDto getClientById(UUID id);
}
