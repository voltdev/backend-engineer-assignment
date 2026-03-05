/* Copyright 2026 XYZ Retail */
package com.xyz.retail.reporting.presentation.controller;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.xyz.retail.reporting.application.port.in.ReportingUseCase;
import com.xyz.retail.reporting.domain.entity.DailySalesReport;
import com.xyz.retail.reporting.domain.entity.ProductSalesReport;
import com.xyz.retail.reporting.presentation.dto.DailySalesReportDto;
import com.xyz.retail.reporting.presentation.dto.DateRangeRequestDto;
import com.xyz.retail.reporting.presentation.dto.ProductSalesReportDto;

class ReportingControllerTest {

  @Mock private ReportingUseCase reportingUseCase;

  private ReportingController controller;

  @BeforeEach
  void setUp() {
    MockitoAnnotations.openMocks(this);
    controller = new ReportingController(reportingUseCase);
  }

  @Test
  void shouldGetTopSellingProducts() {
    // Given
    int limit = 5;
    List<ProductSalesReport> products =
        List.of(
            new ProductSalesReport(
                UUID.randomUUID(),
                "Product1",
                10,
                BigDecimal.TEN,
                false,
                5,
                BigDecimal.valueOf(50)));
    when(reportingUseCase.getTopSellingProducts(limit)).thenReturn(products);

    // When
    List<ProductSalesReportDto> result = controller.getTopSellingProducts(limit);

    // Then
    assertEquals(1, result.size());
    assertEquals("Product1", result.get(0).productName());
    verify(reportingUseCase).getTopSellingProducts(limit);
  }

  @Test
  void shouldGetLeastSellingProducts() {
    // Given
    int limit = 5;
    List<ProductSalesReport> products = List.of();
    when(reportingUseCase.getLeastSellingProducts(limit)).thenReturn(products);

    // When
    List<ProductSalesReportDto> result = controller.getLeastSellingProducts(limit);

    // Then
    assertEquals(0, result.size());
    verify(reportingUseCase).getLeastSellingProducts(limit);
  }

  @Test
  void shouldGetSalesByDateRange() {
    // Given
    LocalDate startDate = LocalDate.of(2026, 1, 1);
    LocalDate endDate = LocalDate.of(2026, 1, 31);
    DateRangeRequestDto dateRange = new DateRangeRequestDto(startDate, endDate);
    List<DailySalesReport> sales =
        List.of(new DailySalesReport(startDate, BigDecimal.valueOf(1000), 10));
    when(reportingUseCase.getSalesReportByDateRange(any())).thenReturn(sales);

    // When
    List<DailySalesReportDto> result = controller.getSalesByDateRange(dateRange);

    // Then
    assertEquals(1, result.size());
    assertEquals(startDate, result.get(0).date());
    verify(reportingUseCase).getSalesReportByDateRange(any());
  }
}
