/* Copyright 2026 XYZ Retail */
package com.xyz.retail.product.presentation.dto;

import java.math.BigDecimal;
import java.util.UUID;

public record ProductResponseDto(
    UUID id, String name, BigDecimal price, int quantity, boolean lowStock) {}
