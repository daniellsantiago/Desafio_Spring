package com.grupo2.desafiospring.service;

import com.grupo2.desafiospring.dto.ClientDto;
import com.grupo2.desafiospring.dto.ListClientParamsDto;
import com.grupo2.desafiospring.model.Client;

import java.io.IOException;
import java.util.List;

public interface ClientService {
    List<ClientDto> addClient(List<Client> client) throws IOException;
    List<Client> listClients(ListClientParamsDto params);
    Client getClientById(Long id);
}
