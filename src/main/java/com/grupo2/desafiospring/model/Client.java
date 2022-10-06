package com.grupo2.desafiospring.model;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Client {
    private Long id;
    private String name;
    private String cpf;
    private String state;
}
