package com.grupo2.desafiospring.repository;

import com.fasterxml.jackson.core.util.DefaultPrettyPrinter;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.grupo2.desafiospring.dto.ListClientParamsDto;
import com.grupo2.desafiospring.model.Client;
import org.springframework.stereotype.Repository;

import java.io.File;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

@Repository
public class ClientRepository {
    private final String PATH_NAME = "src/main/resources/data/clients.json";

    private final ObjectMapper objectMapper;

    public ClientRepository(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }
    public List<Client> getAllClients(ListClientParamsDto params) throws IOException {
        List<Client> clients = Arrays.asList(objectMapper.readValue(new File(PATH_NAME), Client[].class));;
        if (params == null){
            return clients;
        }
        return clients.stream().filter(client -> {
            if(Objects.nonNull(params.getState())){
                return params.getState().equalsIgnoreCase(client.getState());
            }
            return true;
        }).collect(Collectors.toList());
    }

    public List<Client> addClientRepository(List<Client> client) throws IOException {
        ObjectWriter writer = objectMapper.writer(new DefaultPrettyPrinter());
        List<Client> clientList = new ArrayList<>(getAllClients(null));

        clientList.addAll(client);
        writer.writeValue(new File(PATH_NAME), clientList);

        return clientList;
    }

    public Optional<Client> getClientById(UUID id) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        Client[] clientList = mapper.readValue(new File(PATH_NAME), Client[].class);
        for (Client foundClient : clientList) {
            if (foundClient.getId().equals(id)) {
                return Optional.of(foundClient);
            }
        }
        return Optional.empty();
    }
}
