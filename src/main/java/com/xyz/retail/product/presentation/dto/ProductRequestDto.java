package com.xyz.retail.product.presentation.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;

public record ProductRequestDto(

        @NotBlank(message = "Product name is required")
        String name,

        @NotNull(message = "Price is required")
        BigDecimal price,

        @Min(value = 0, message = "Quantity cannot be negative")
        int quantity
) {}