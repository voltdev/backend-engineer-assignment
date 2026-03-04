/* Copyright 2026 XYZ Retail */
package com.xyz.retail.reporting.application.port.out;

import com.xyz.retail.order.domain.entity.Order;

public interface SaveSalesReportPort {
  void recordSalesFromOrder(Order order);
}
