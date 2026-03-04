/* Copyright 2026 XYZ Retail */
package com.xyz.retail.reporting.infrastructure.adapter.persistence;

import java.time.LocalDate;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.xyz.retail.order.domain.entity.Order;
import com.xyz.retail.order.domain.entity.OrderItem;
import com.xyz.retail.reporting.application.port.out.SaveSalesReportPort;

@Component
public class SalesReportingAdapter implements SaveSalesReportPort {

  private final SalesReportRepository salesReportRepository;

  public SalesReportingAdapter(SalesReportRepository salesReportRepository) {
    this.salesReportRepository = salesReportRepository;
  }

  @Override
  @Transactional
  public void recordSalesFromOrder(Order order) {
    LocalDate orderDate = order.getCreatedAt().toLocalDate();

    for (OrderItem item : order.getItems()) {
      SalesReportJpaEntity salesReport = new SalesReportJpaEntity();
      salesReport.setDate(orderDate);
      salesReport.setProductId(item.getProductId());
      salesReport.setProductName(item.getProductName());
      salesReport.setQuantitySold(item.getQuantity());
      salesReport.setTotalSales(item.getSubtotal());

      salesReportRepository.save(salesReport);
    }
  }
}
