package com.grupo2.desafiospring.controller;

import com.grupo2.desafiospring.dto.ClientDto;
import com.grupo2.desafiospring.dto.ListClientParamsDto;
import com.grupo2.desafiospring.dto.RegisterClientDto;
import com.grupo2.desafiospring.model.Client;
import com.grupo2.desafiospring.service.ClientService;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("api/v1/clients")
@Validated
public class ClientController {

    private final ClientService clientService;

    public ClientController(ClientService clientService){
        this.clientService = clientService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
        public List<ClientDto> setClient(@RequestBody @Valid @NotEmpty List<@Valid RegisterClientDto> clientsDto) {
        return clientService.addClient(clientsDto);
    }

    @GetMapping
    public List<Client> getClients(@Valid ListClientParamsDto params) {
        return clientService.listClients(params);
    }

    @GetMapping("/{id}")
    public ClientDto getClientById(@PathVariable UUID id) {
        return clientService.getClientById(id);
    }
}
