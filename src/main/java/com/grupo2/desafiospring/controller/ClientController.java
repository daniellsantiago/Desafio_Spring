package com.grupo2.desafiospring.controller;

import com.grupo2.desafiospring.dto.ClientDto;
import com.grupo2.desafiospring.dto.ListClientParamsDto;
import com.grupo2.desafiospring.model.Client;
import com.grupo2.desafiospring.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("api/v1/clients")
public class ClientController {

    private final ClientService clientService;

    public ClientController(ClientService clientService){
        this.clientService = clientService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
        public List<ClientDto> setClient(@RequestBody List<Client> client) throws IOException {
        return clientService.addClient(client);
    }

    @GetMapping
    public List<Client> getClients(ListClientParamsDto params) {
        return clientService.listClients(params);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Client> getClientById(@PathVariable Long id) {
        return ResponseEntity.ok(clientService.getClientById(id));
    }
}
