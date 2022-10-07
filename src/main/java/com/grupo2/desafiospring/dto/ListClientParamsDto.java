package com.grupo2.desafiospring.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Size;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ListClientParamsDto {
    @Size(min = 2, max = 2)
    private String state;
}
