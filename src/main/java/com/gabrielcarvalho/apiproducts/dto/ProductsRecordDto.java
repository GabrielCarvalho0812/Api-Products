package com.gabrielcarvalho.apiproducts.dto;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;

public record ProductsRecordDto(@NotBlank
                                String name,

                                @NotNull
                                BigDecimal value) {

}
