/* Copyright 2026 XYZ Retail */
package com.xyz.retail.reporting.infrastructure.adapter.persistence;

import static org.junit.jupiter.api.Assertions.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

import org.junit.jupiter.api.Test;

class SalesReportJpaEntityTest {

  @Test
  void shouldCreateSalesReportJpaEntity() {
    LocalDate date = LocalDate.now();
    UUID productId = UUID.randomUUID();
    String productName = "Product";
    int quantitySold = 5;
    BigDecimal totalSales = BigDecimal.valueOf(50.0);

    SalesReportJpaEntity entity = new SalesReportJpaEntity();
    entity.setDate(date);
    entity.setProductId(productId);
    entity.setProductName(productName);
    entity.setQuantitySold(quantitySold);
    entity.setTotalSales(totalSales);

    assertEquals(date, entity.getDate());
    assertEquals(productId, entity.getProductId());
    assertEquals(productName, entity.getProductName());
    assertEquals(quantitySold, entity.getQuantitySold());
    assertEquals(totalSales, entity.getTotalSales());
  }

  @Test
  void shouldSetAndGetId() {
    SalesReportJpaEntity entity = new SalesReportJpaEntity();
    Long id = 1L;

    entity.setId(id);

    assertEquals(id, entity.getId());
  }
}
