package com.grupo2.desafiospring.dto;

import com.grupo2.desafiospring.model.Client;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RegisterClientDto {
    @NotBlank
    private String name;

    @NotBlank
    private String cpf;

    @NotBlank
    @Size(min = 2, max = 2)
    private String state;

    public Client toClient(UUID clientId) {
        return new Client(
                clientId,
                this.name,
                this.cpf,
                this.state
        );
    }
}
