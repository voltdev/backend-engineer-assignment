/* Copyright 2026 XYZ Retail */
package com.xyz.retail.reporting.application.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.xyz.retail.reporting.application.port.in.query.DateRangeQuery;
import com.xyz.retail.reporting.application.port.out.LoadSalesReportPort;
import com.xyz.retail.reporting.domain.entity.DailySalesReport;
import com.xyz.retail.reporting.domain.entity.ProductSalesReport;

class ReportingServiceTest {

  @Mock private LoadSalesReportPort loadSalesReportPort;

  private ReportingService reportingService;

  @BeforeEach
  void setUp() {
    MockitoAnnotations.openMocks(this);
    reportingService = new ReportingService(loadSalesReportPort);
  }

  @Test
  void shouldGetTopSellingProducts() {
    // Given
    int limit = 5;
    List<ProductSalesReport> topProducts =
        List.of(
            new ProductSalesReport(
                UUID.randomUUID(),
                "Product1",
                10,
                BigDecimal.TEN,
                false,
                5,
                BigDecimal.valueOf(50)));
    when(loadSalesReportPort.findTopSellingProductsByDate(any(LocalDate.class), anyInt()))
        .thenReturn(topProducts);

    // When
    List<ProductSalesReport> result = reportingService.getTopSellingProducts(limit);

    // Then
    assertEquals(1, result.size());
    verify(loadSalesReportPort).findTopSellingProductsByDate(any(LocalDate.class), eq(limit));
  }

  @Test
  void shouldGetLeastSellingProducts() {
    // Given
    int limit = 5;
    List<ProductSalesReport> leastProducts = List.of();
    when(loadSalesReportPort.findLeastSellingProductsByDateRange(any(), any(), anyInt()))
        .thenReturn(leastProducts);

    // When
    List<ProductSalesReport> result = reportingService.getLeastSellingProducts(limit);

    // Then
    assertEquals(0, result.size());
    verify(loadSalesReportPort).findLeastSellingProductsByDateRange(any(), any(), eq(limit));
  }

  @Test
  void shouldGetSalesReportByDateRange() {
    // Given
    LocalDate startDate = LocalDate.of(2026, 1, 1);
    LocalDate endDate = LocalDate.of(2026, 1, 31);
    DateRangeQuery query = new DateRangeQuery(startDate, endDate);
    List<DailySalesReport> reports =
        List.of(new DailySalesReport(LocalDate.of(2026, 1, 1), BigDecimal.valueOf(1000), 10));
    when(loadSalesReportPort.findSalesByDateRange(startDate, endDate)).thenReturn(reports);

    // When
    List<DailySalesReport> result = reportingService.getSalesReportByDateRange(query);

    // Then
    assertEquals(1, result.size());
    verify(loadSalesReportPort).findSalesByDateRange(startDate, endDate);
  }
}
