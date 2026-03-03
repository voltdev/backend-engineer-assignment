package com.xyz.retail.cart.presentation.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;
import java.util.UUID;

public record AddToCartRequestDto(
        @NotNull(message = "Product ID is required")
        UUID productId,

        @NotBlank(message = "Product name is required")
        String productName,

        @NotNull(message = "Product price is required")
        BigDecimal productPrice,

        @NotNull(message = "Quantity is required")
        @Min(value = 1, message = "Quantity must be at least 1")
        Integer quantity
) {}