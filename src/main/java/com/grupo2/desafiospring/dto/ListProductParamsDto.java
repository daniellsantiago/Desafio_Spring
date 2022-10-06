package com.grupo2.desafiospring.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ListProductParamsDto {
    private String category;
    private Boolean freeShipping;
    private String prestige;
    private Integer order;
}
