package com.grupo2.desafiospring.dto;

import com.grupo2.desafiospring.model.Client;
import lombok.*;

import java.io.Serializable;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ClientDto implements Serializable {
    private UUID id;
    private String name;
    private String cpf;
    private String state;

    public ClientDto(Client client){
        this.id = client.getId();
        this.name = client.getName();
        this.cpf = client.getCpf();
        this.state = client.getState();
    }

    public static ClientDto toClientDto(Client client) {
        return new ClientDto(
                client.getId(),
                client.getName(),
                client.getCpf(),
                client.getState()
        );
    }

    public static List<ClientDto> fromClientList(List<Client> clients) {
        return clients.stream().map(ClientDto::new).collect(Collectors.toList());
    }
}
