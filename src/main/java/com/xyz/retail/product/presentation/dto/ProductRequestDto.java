/* Copyright 2026 XYZ Retail */
package com.xyz.retail.product.presentation.dto;

import java.math.BigDecimal;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record ProductRequestDto(
    @NotBlank(message = "Product name is required") String name,
    @NotNull(message = "Price is required") BigDecimal price,
    @Min(value = 0, message = "Quantity cannot be negative") int quantity) {}
