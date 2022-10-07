package com.grupo2.desafiospring.repository;

import com.fasterxml.jackson.core.util.DefaultPrettyPrinter;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.grupo2.desafiospring.dto.ListClientParamsDto;
import com.grupo2.desafiospring.model.Client;
import org.springframework.stereotype.Repository;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
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

    public Client getClientById(Long id) {
        ObjectMapper mapper = new ObjectMapper();
        Client client = null;

        try {
            List<Client> clientList = Arrays.asList(mapper.readValue(new File(PATH_NAME), Client[].class));
            for (Client clients : clientList) {
                if (clients.getId().equals(id)) {
                    return client = clients;
                }
            }
        } catch (Exception e) {
        }
        return client;
    }
}
