/* Copyright 2026 XYZ Retail */
package com.xyz.retail.reporting.infrastructure.adapter.persistence;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import com.xyz.retail.product.application.port.out.LoadProductPort;
import com.xyz.retail.product.service.domain.entity.Product;
import com.xyz.retail.reporting.application.port.out.LoadSalesReportPort;
import com.xyz.retail.reporting.domain.entity.DailySalesReport;
import com.xyz.retail.reporting.domain.entity.ProductSalesReport;

@Component
public class ReportingPersistenceAdapter implements LoadSalesReportPort {

  private final SalesReportRepository salesReportRepository;
  private final LoadProductPort loadProductPort;
  private static final int LOW_STOCK_THRESHOLD = 10;

  public ReportingPersistenceAdapter(
      SalesReportRepository salesReportRepository, LoadProductPort loadProductPort) {
    this.salesReportRepository = salesReportRepository;
    this.loadProductPort = loadProductPort;
  }

  @Override
  public List<ProductSalesReport> findTopSellingProductsByDate(LocalDate date, int limit) {
    List<SalesReportJpaEntity> topSelling = salesReportRepository.findTopSellingByDate(date);

    // Limit the results
    if (topSelling.size() > limit) {
      topSelling = topSelling.subList(0, limit);
    }

    return mapToProductSalesReports(topSelling);
  }

  @Override
  public List<ProductSalesReport> findLeastSellingProductsByDateRange(
      LocalDate startDate, LocalDate endDate, int limit) {
    List<SalesReportJpaEntity> leastSelling =
        salesReportRepository.findLeastSellingByDateRange(startDate, endDate);

    // Limit the results
    if (leastSelling.size() > limit) {
      leastSelling = leastSelling.subList(0, limit);
    }

    return mapToProductSalesReports(leastSelling);
  }

  @Override
  public List<DailySalesReport> findSalesByDateRange(LocalDate startDate, LocalDate endDate) {
    // Create a map to store daily sales
    Map<LocalDate, DailySalesReport> dailySalesMap = new HashMap<>();

    // Initialize the map with all dates in the range
    LocalDate currentDate = startDate;
    while (!currentDate.isAfter(endDate)) {
      BigDecimal totalSales = salesReportRepository.findTotalSalesByDate(currentDate);
      int orderCount = salesReportRepository.findOrderCountByDate(currentDate);

      // If no sales for this day, use zero
      if (totalSales == null) {
        totalSales = BigDecimal.ZERO;
      }

      dailySalesMap.put(currentDate, new DailySalesReport(currentDate, totalSales, orderCount));
      currentDate = currentDate.plusDays(1);
    }

    // Return sorted by date
    return dailySalesMap.values().stream()
        .sorted(Comparator.comparing(DailySalesReport::getDate))
        .collect(Collectors.toList());
  }

  private List<ProductSalesReport> mapToProductSalesReports(
      List<SalesReportJpaEntity> salesEntities) {
    List<ProductSalesReport> result = new ArrayList<>();

    for (SalesReportJpaEntity entity : salesEntities) {
      // Get current product information
      Optional<Product> productOpt = loadProductPort.loadProductById(entity.getProductId());

      if (productOpt.isPresent()) {
        Product product = productOpt.get();

        result.add(
            new ProductSalesReport(
                product.getId().value(),
                product.getName(),
                product.getQuantity(),
                product.getPrice(),
                product.getQuantity() < LOW_STOCK_THRESHOLD,
                entity.getQuantitySold(),
                entity.getTotalSales()));
      } else {
        // Product no longer exists, but we still want to show sales data
        result.add(
            new ProductSalesReport(
                entity.getProductId(),
                entity.getProductName() + " (Discontinued)",
                0,
                BigDecimal.ZERO,
                true,
                entity.getQuantitySold(),
                entity.getTotalSales()));
      }
    }

    return result;
  }
}
