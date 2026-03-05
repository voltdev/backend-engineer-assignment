/* Copyright 2026 XYZ Retail */
package com.xyz.retail.reporting.domain.entity;

import static org.junit.jupiter.api.Assertions.*;

import java.math.BigDecimal;
import java.util.UUID;

import org.junit.jupiter.api.Test;

class ProductSalesReportTest {

  @Test
  void shouldCreateProductSalesReport() {
    UUID productId = UUID.randomUUID();
    String productName = "Test Product";
    int quantityAvailable = 20;
    BigDecimal price = BigDecimal.valueOf(15.99);
    boolean lowStock = false;
    int quantitySold = 5;
    BigDecimal totalSales = BigDecimal.valueOf(79.95);

    ProductSalesReport report =
        new ProductSalesReport(
            productId, productName, quantityAvailable, price, lowStock, quantitySold, totalSales);

    assertEquals(productId, report.getProductId());
    assertEquals(productName, report.getProductName());
    assertEquals(quantityAvailable, report.getQuantityAvailable());
    assertEquals(price, report.getPrice());
    assertEquals(lowStock, report.isLowStock());
    assertEquals(quantitySold, report.getQuantitySold());
    assertEquals(totalSales, report.getTotalSales());
  }

  @Test
  void shouldAllowNullValues() {
    ProductSalesReport report = new ProductSalesReport(null, null, 0, null, true, 0, null);
    assertNull(report.getProductId());
    assertNull(report.getProductName());
    assertEquals(0, report.getQuantityAvailable());
    assertNull(report.getPrice());
    assertTrue(report.isLowStock());
    assertEquals(0, report.getQuantitySold());
    assertNull(report.getTotalSales());
  }
}
