package com.gabrielcarvalho.apiproducts.dto;


import jakarta.validation.constraints.NotBlank;
import org.springframework.lang.NonNull;

import java.math.BigDecimal;

public record ProductsRecordDto(@NotBlank String name, @NonNull BigDecimal value) {
}
