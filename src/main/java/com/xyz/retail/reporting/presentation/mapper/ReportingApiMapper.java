/* Copyright 2026 XYZ Retail */
package com.xyz.retail.reporting.presentation.mapper;

import java.util.List;
import java.util.stream.Collectors;

import com.xyz.retail.reporting.application.port.in.query.DateRangeQuery;
import com.xyz.retail.reporting.domain.entity.DailySalesReport;
import com.xyz.retail.reporting.domain.entity.ProductSalesReport;
import com.xyz.retail.reporting.presentation.dto.DailySalesReportDto;
import com.xyz.retail.reporting.presentation.dto.DateRangeRequestDto;
import com.xyz.retail.reporting.presentation.dto.ProductSalesReportDto;

public class ReportingApiMapper {

  public static DateRangeQuery toQuery(DateRangeRequestDto dto) {
    return new DateRangeQuery(dto.startDate(), dto.endDate());
  }

  public static ProductSalesReportDto toDto(ProductSalesReport report) {
    return new ProductSalesReportDto(
        report.getProductId(),
        report.getProductName(),
        report.getQuantityAvailable(),
        report.getPrice(),
        report.isLowStock(),
        report.getQuantitySold(),
        report.getTotalSales());
  }

  public static List<ProductSalesReportDto> toProductDtoList(List<ProductSalesReport> reports) {
    return reports.stream().map(ReportingApiMapper::toDto).collect(Collectors.toList());
  }

  public static DailySalesReportDto toDto(DailySalesReport report) {
    return new DailySalesReportDto(
        report.getDate(), report.getTotalSales(), report.getOrderCount());
  }

  public static List<DailySalesReportDto> toDailySalesDtoList(List<DailySalesReport> reports) {
    return reports.stream().map(ReportingApiMapper::toDto).collect(Collectors.toList());
  }
}
