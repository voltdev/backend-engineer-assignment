/* Copyright 2026 XYZ Retail */
package com.xyz.retail.reporting.application.port.in;

import java.util.List;

import com.xyz.retail.reporting.application.port.in.query.DateRangeQuery;
import com.xyz.retail.reporting.domain.entity.DailySalesReport;
import com.xyz.retail.reporting.domain.entity.ProductSalesReport;

public interface ReportingUseCase {
  List<ProductSalesReport> getTopSellingProducts(int limit);

  List<ProductSalesReport> getLeastSellingProducts(int limit);

  List<DailySalesReport> getSalesReportByDateRange(DateRangeQuery query);
}
