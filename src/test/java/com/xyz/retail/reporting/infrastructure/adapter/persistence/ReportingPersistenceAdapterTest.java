/* Copyright 2026 XYZ Retail */
package com.xyz.retail.reporting.infrastructure.adapter.persistence;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.xyz.retail.product.application.port.out.LoadProductPort;
import com.xyz.retail.reporting.domain.entity.DailySalesReport;
import com.xyz.retail.reporting.domain.entity.ProductSalesReport;

class ReportingPersistenceAdapterTest {

  @Mock private SalesReportRepository salesReportRepository;

  @Mock private LoadProductPort loadProductPort;

  private ReportingPersistenceAdapter adapter;

  @BeforeEach
  void setUp() {
    MockitoAnnotations.openMocks(this);
    adapter = new ReportingPersistenceAdapter(salesReportRepository, loadProductPort);
  }

  @Test
  void shouldFindTopSellingProductsByDate() {
    // Given
    LocalDate date = LocalDate.now();
    int limit = 5;
    SalesReportJpaEntity entity = new SalesReportJpaEntity();
    entity.setProductId(UUID.randomUUID());
    entity.setProductName("Top Product");
    entity.setQuantitySold(100);
    entity.setTotalSales(BigDecimal.valueOf(1000));
    when(salesReportRepository.findTopSellingByDate(date)).thenReturn(List.of(entity));

    // When
    List<ProductSalesReport> result = adapter.findTopSellingProductsByDate(date, limit);

    // Then
    assertNotNull(result);
    verify(salesReportRepository).findTopSellingByDate(date);
  }

  @Test
  void shouldFindLeastSellingProductsByDateRange() {
    // Given
    LocalDate startDate = LocalDate.of(2026, 1, 1);
    LocalDate endDate = LocalDate.of(2026, 1, 31);
    int limit = 5;
    when(salesReportRepository.findLeastSellingByDateRange(startDate, endDate))
        .thenReturn(List.of());

    // When
    List<ProductSalesReport> result =
        adapter.findLeastSellingProductsByDateRange(startDate, endDate, limit);

    // Then
    assertEquals(0, result.size());
    verify(salesReportRepository).findLeastSellingByDateRange(startDate, endDate);
  }

  @Test
  void shouldFindSalesByDateRange() {
    // Given
    LocalDate startDate = LocalDate.of(2026, 1, 1);
    LocalDate endDate = LocalDate.of(2026, 1, 1);
    when(salesReportRepository.findTotalSalesByDate(startDate))
        .thenReturn(BigDecimal.valueOf(1000));
    when(salesReportRepository.findOrderCountByDate(startDate)).thenReturn(10);

    // When
    List<DailySalesReport> result = adapter.findSalesByDateRange(startDate, endDate);

    // Then
    assertNotNull(result);
    assertEquals(1, result.size());
    verify(salesReportRepository).findTotalSalesByDate(startDate);
    verify(salesReportRepository).findOrderCountByDate(startDate);
  }
}
