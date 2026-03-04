/* Copyright 2026 XYZ Retail */
package com.xyz.retail.reporting.presentation.controller;

import java.util.List;

import org.springframework.web.bind.annotation.*;

import com.xyz.retail.reporting.application.port.in.ReportingUseCase;
import com.xyz.retail.reporting.domain.entity.DailySalesReport;
import com.xyz.retail.reporting.domain.entity.ProductSalesReport;
import com.xyz.retail.reporting.presentation.dto.DailySalesReportDto;
import com.xyz.retail.reporting.presentation.dto.DateRangeRequestDto;
import com.xyz.retail.reporting.presentation.dto.ProductSalesReportDto;
import com.xyz.retail.reporting.presentation.mapper.ReportingApiMapper;

import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/v1/reports")
public class ReportingController {

  private final ReportingUseCase reportingUseCase;
  private static final int DEFAULT_TOP_PRODUCTS_LIMIT = 5;
  private static final int DEFAULT_LEAST_PRODUCTS_LIMIT = 5;

  public ReportingController(ReportingUseCase reportingUseCase) {
    this.reportingUseCase = reportingUseCase;
  }

  @Operation(summary = "Get top selling products of the day")
  @GetMapping("/products/top-selling")
  public List<ProductSalesReportDto> getTopSellingProducts(
      @RequestParam(defaultValue = "5") int limit) {
    List<ProductSalesReport> reports = reportingUseCase.getTopSellingProducts(limit);
    return ReportingApiMapper.toProductDtoList(reports);
  }

  @Operation(summary = "Get least selling products of the month")
  @GetMapping("/products/least-selling")
  public List<ProductSalesReportDto> getLeastSellingProducts(
      @RequestParam(defaultValue = "5") int limit) {
    List<ProductSalesReport> reports = reportingUseCase.getLeastSellingProducts(limit);
    return ReportingApiMapper.toProductDtoList(reports);
  }

  @Operation(summary = "Get sales report by date range")
  @PostMapping("/sales/by-date-range")
  public List<DailySalesReportDto> getSalesByDateRange(
      @Valid @RequestBody DateRangeRequestDto dateRange) {
    List<DailySalesReport> reports =
        reportingUseCase.getSalesReportByDateRange(ReportingApiMapper.toQuery(dateRange));
    return ReportingApiMapper.toDailySalesDtoList(reports);
  }
}
