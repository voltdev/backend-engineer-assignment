/* Copyright 2026 XYZ Retail */
package com.xyz.retail.reporting.presentation.dto;

import static org.junit.jupiter.api.Assertions.*;

import java.math.BigDecimal;
import java.util.UUID;

import org.junit.jupiter.api.Test;

class ProductSalesReportDtoTest {

  @Test
  void shouldCreateProductSalesReportDto() {
    UUID productId = UUID.randomUUID();
    String productName = "Product";
    int quantityAvailable = 20;
    BigDecimal price = BigDecimal.valueOf(10.0);
    boolean lowStock = false;
    int quantitySold = 5;
    BigDecimal totalSales = BigDecimal.valueOf(50.0);

    ProductSalesReportDto dto =
        new ProductSalesReportDto(
            productId, productName, quantityAvailable, price, lowStock, quantitySold, totalSales);

    assertEquals(productId, dto.productId());
    assertEquals(productName, dto.productName());
    assertEquals(quantityAvailable, dto.quantityAvailable());
    assertEquals(price, dto.price());
    assertEquals(lowStock, dto.lowStock());
    assertEquals(quantitySold, dto.quantitySold());
    assertEquals(totalSales, dto.totalSales());
  }

  @Test
  void shouldAllowNullValues() {
    ProductSalesReportDto dto = new ProductSalesReportDto(null, null, 0, null, true, 0, null);
    assertNull(dto.productId());
    assertNull(dto.productName());
    assertEquals(0, dto.quantityAvailable());
    assertNull(dto.price());
    assertTrue(dto.lowStock());
    assertEquals(0, dto.quantitySold());
    assertNull(dto.totalSales());
  }
}
