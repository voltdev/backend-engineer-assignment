/* Copyright 2026 XYZ Retail */
package com.xyz.retail.reporting.presentation.mapper;

import static org.junit.jupiter.api.Assertions.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

import org.junit.jupiter.api.Test;

import com.xyz.retail.reporting.application.port.in.query.DateRangeQuery;
import com.xyz.retail.reporting.domain.entity.DailySalesReport;
import com.xyz.retail.reporting.domain.entity.ProductSalesReport;
import com.xyz.retail.reporting.presentation.dto.DailySalesReportDto;
import com.xyz.retail.reporting.presentation.dto.DateRangeRequestDto;
import com.xyz.retail.reporting.presentation.dto.ProductSalesReportDto;

class ReportingApiMapperTest {

  @Test
  void shouldMapDateRangeRequestDtoToQuery() {
    // Given
    LocalDate startDate = LocalDate.of(2026, 1, 1);
    LocalDate endDate = LocalDate.of(2026, 1, 31);
    DateRangeRequestDto dto = new DateRangeRequestDto(startDate, endDate);

    // When
    DateRangeQuery query = ReportingApiMapper.toQuery(dto);

    // Then
    assertEquals(startDate, query.startDate());
    assertEquals(endDate, query.endDate());
  }

  @Test
  void shouldMapProductSalesReportToDto() {
    // Given
    UUID productId = UUID.randomUUID();
    ProductSalesReport report =
        new ProductSalesReport(
            productId, "Product", 10, BigDecimal.ONE, false, 5, BigDecimal.valueOf(5));

    // When
    ProductSalesReportDto dto = ReportingApiMapper.toDto(report);

    // Then
    assertEquals(productId, dto.productId());
    assertEquals("Product", dto.productName());
    assertEquals(10, dto.quantityAvailable());
    assertEquals(BigDecimal.ONE, dto.price());
    assertFalse(dto.lowStock());
    assertEquals(5, dto.quantitySold());
    assertEquals(BigDecimal.valueOf(5), dto.totalSales());
  }

  @Test
  void shouldMapProductListToDto() {
    // Given
    List<ProductSalesReport> reports =
        List.of(
            new ProductSalesReport(
                UUID.randomUUID(), "Product1", 10, BigDecimal.ONE, false, 5, BigDecimal.valueOf(5)),
            new ProductSalesReport(
                UUID.randomUUID(), "Product2", 5, BigDecimal.TEN, true, 2, BigDecimal.valueOf(20)));

    // When
    List<ProductSalesReportDto> dtos = ReportingApiMapper.toProductDtoList(reports);

    // Then
    assertEquals(2, dtos.size());
    assertEquals("Product1", dtos.get(0).productName());
    assertEquals("Product2", dtos.get(1).productName());
  }

  @Test
  void shouldMapDailySalesReportToDto() {
    // Given
    LocalDate date = LocalDate.of(2026, 1, 1);
    DailySalesReport report = new DailySalesReport(date, BigDecimal.valueOf(1000), 10);

    // When
    DailySalesReportDto dto = ReportingApiMapper.toDto(report);

    // Then
    assertEquals(date, dto.date());
    assertEquals(BigDecimal.valueOf(1000), dto.totalSales());
    assertEquals(10, dto.orderCount());
  }

  @Test
  void shouldMapDailySalesListToDto() {
    // Given
    List<DailySalesReport> reports =
        List.of(
            new DailySalesReport(LocalDate.of(2026, 1, 1), BigDecimal.valueOf(1000), 10),
            new DailySalesReport(LocalDate.of(2026, 1, 2), BigDecimal.valueOf(1500), 15));

    // When
    List<DailySalesReportDto> dtos = ReportingApiMapper.toDailySalesDtoList(reports);

    // Then
    assertEquals(2, dtos.size());
    assertEquals(BigDecimal.valueOf(1000), dtos.get(0).totalSales());
    assertEquals(BigDecimal.valueOf(1500), dtos.get(1).totalSales());
  }
}
