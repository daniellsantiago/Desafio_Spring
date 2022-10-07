package com.grupo2.desafiospring.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductPurchaseListDto {
    @NotEmpty
    @Valid
    private List<ProductPurchaseItemDto> productsPurchaseRequest;
}
