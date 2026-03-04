/* Copyright 2026 XYZ Retail */
package com.xyz.retail.reporting.domain.entity;

import java.math.BigDecimal;
import java.util.UUID;

public class ProductSalesReport {
  private final UUID productId;
  private final String productName;
  private final int quantityAvailable;
  private final BigDecimal price;
  private final boolean lowStock;
  private final int quantitySold;
  private final BigDecimal totalSales;

  public ProductSalesReport(
      UUID productId,
      String productName,
      int quantityAvailable,
      BigDecimal price,
      boolean lowStock,
      int quantitySold,
      BigDecimal totalSales) {
    this.productId = productId;
    this.productName = productName;
    this.quantityAvailable = quantityAvailable;
    this.price = price;
    this.lowStock = lowStock;
    this.quantitySold = quantitySold;
    this.totalSales = totalSales;
  }

  public UUID getProductId() {
    return productId;
  }

  public String getProductName() {
    return productName;
  }

  public int getQuantityAvailable() {
    return quantityAvailable;
  }

  public BigDecimal getPrice() {
    return price;
  }

  public boolean isLowStock() {
    return lowStock;
  }

  public int getQuantitySold() {
    return quantitySold;
  }

  public BigDecimal getTotalSales() {
    return totalSales;
  }
}
