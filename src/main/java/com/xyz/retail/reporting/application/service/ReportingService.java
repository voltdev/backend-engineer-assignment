/* Copyright 2026 XYZ Retail */
package com.xyz.retail.reporting.application.service;

import java.time.LocalDate;
import java.time.YearMonth;
import java.util.List;

import com.xyz.retail.reporting.application.port.in.ReportingUseCase;
import com.xyz.retail.reporting.application.port.in.query.DateRangeQuery;
import com.xyz.retail.reporting.application.port.out.LoadSalesReportPort;
import com.xyz.retail.reporting.domain.entity.DailySalesReport;
import com.xyz.retail.reporting.domain.entity.ProductSalesReport;

public class ReportingService implements ReportingUseCase {

  private final LoadSalesReportPort loadSalesReportPort;

  public ReportingService(LoadSalesReportPort loadSalesReportPort) {
    this.loadSalesReportPort = loadSalesReportPort;
  }

  @Override
  public List<ProductSalesReport> getTopSellingProducts(int limit) {
    // Get top selling products for today
    return loadSalesReportPort.findTopSellingProductsByDate(LocalDate.now(), limit);
  }

  @Override
  public List<ProductSalesReport> getLeastSellingProducts(int limit) {
    // Get least selling products for the current month
    LocalDate today = LocalDate.now();
    YearMonth currentMonth = YearMonth.from(today);
    LocalDate startOfMonth = currentMonth.atDay(1);
    LocalDate endOfMonth = currentMonth.atEndOfMonth();

    return loadSalesReportPort.findLeastSellingProductsByDateRange(startOfMonth, endOfMonth, limit);
  }

  @Override
  public List<DailySalesReport> getSalesReportByDateRange(DateRangeQuery query) {
    return loadSalesReportPort.findSalesByDateRange(query.startDate(), query.endDate());
  }
}
