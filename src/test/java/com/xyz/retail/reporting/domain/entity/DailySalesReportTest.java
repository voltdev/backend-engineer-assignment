/* Copyright 2026 XYZ Retail */
package com.xyz.retail.reporting.domain.entity;

import static org.junit.jupiter.api.Assertions.*;

import java.math.BigDecimal;
import java.time.LocalDate;

import org.junit.jupiter.api.Test;

class DailySalesReportTest {

  @Test
  void shouldCreateDailySalesReport() {
    LocalDate date = LocalDate.now();
    BigDecimal totalSales = BigDecimal.valueOf(100.50);
    int orderCount = 10;

    DailySalesReport report = new DailySalesReport(date, totalSales, orderCount);

    assertEquals(date, report.getDate());
    assertEquals(totalSales, report.getTotalSales());
    assertEquals(orderCount, report.getOrderCount());
  }

  @Test
  void shouldAllowNullDate() {
    DailySalesReport report = new DailySalesReport(null, BigDecimal.ZERO, 0);
    assertNull(report.getDate());
  }
}
