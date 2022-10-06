package com.grupo2.desafiospring.dto;

import com.grupo2.desafiospring.annotation.PrestigeValidation;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.Size;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ListProductParamsDto {
    private String category;
    private Boolean freeShipping;
    @Size(min = 1, max = 5)
    @PrestigeValidation()
    private String prestige;
    @Min(0)
    @Max(3)
    private Integer order;

    public boolean hasAnyFilterParam() {
        return this.category != null || this.freeShipping != null || this.prestige != null;
    }

    public boolean hasAnySortParam() {
        return this.order != null;
    }
}
