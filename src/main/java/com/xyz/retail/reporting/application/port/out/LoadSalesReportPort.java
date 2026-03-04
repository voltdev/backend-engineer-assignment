/* Copyright 2026 XYZ Retail */
package com.xyz.retail.reporting.application.port.out;

import java.time.LocalDate;
import java.util.List;

import com.xyz.retail.reporting.domain.entity.DailySalesReport;
import com.xyz.retail.reporting.domain.entity.ProductSalesReport;

public interface LoadSalesReportPort {
  List<ProductSalesReport> findTopSellingProductsByDate(LocalDate date, int limit);

  List<ProductSalesReport> findLeastSellingProductsByDateRange(
      LocalDate startDate, LocalDate endDate, int limit);

  List<DailySalesReport> findSalesByDateRange(LocalDate startDate, LocalDate endDate);
}
