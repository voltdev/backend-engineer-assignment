/* Copyright 2026 XYZ Retail */
package com.xyz.retail.reporting.domain.entity;

import java.math.BigDecimal;
import java.time.LocalDate;

public class DailySalesReport {
  private final LocalDate date;
  private final BigDecimal totalSales;
  private final int orderCount;

  public DailySalesReport(LocalDate date, BigDecimal totalSales, int orderCount) {
    this.date = date;
    this.totalSales = totalSales;
    this.orderCount = orderCount;
  }

  public LocalDate getDate() {
    return date;
  }

  public BigDecimal getTotalSales() {
    return totalSales;
  }

  public int getOrderCount() {
    return orderCount;
  }
}
