/* Copyright 2026 XYZ Retail */
package com.xyz.retail.reporting.presentation.dto;

import static org.junit.jupiter.api.Assertions.*;

import java.math.BigDecimal;
import java.time.LocalDate;

import org.junit.jupiter.api.Test;

class DailySalesReportDtoTest {

  @Test
  void shouldCreateDailySalesReportDto() {
    LocalDate date = LocalDate.now();
    BigDecimal totalSales = BigDecimal.valueOf(1000.0);
    int orderCount = 10;

    DailySalesReportDto dto = new DailySalesReportDto(date, totalSales, orderCount);

    assertEquals(date, dto.date());
    assertEquals(totalSales, dto.totalSales());
    assertEquals(orderCount, dto.orderCount());
  }

  @Test
  void shouldAllowNullValues() {
    DailySalesReportDto dto = new DailySalesReportDto(null, null, 0);
    assertNull(dto.date());
    assertNull(dto.totalSales());
    assertEquals(0, dto.orderCount());
  }
}
