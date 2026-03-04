/* Copyright 2026 XYZ Retail */
package com.xyz.retail.reporting.presentation.dto;

import java.math.BigDecimal;
import java.util.UUID;

public record ProductSalesReportDto(
    UUID productId,
    String productName,
    int quantityAvailable,
    BigDecimal price,
    boolean lowStock,
    int quantitySold,
    BigDecimal totalSales) {}
