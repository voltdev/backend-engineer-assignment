/* Copyright 2026 XYZ Retail */
package com.xyz.retail.reporting.infrastructure.adapter.persistence;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

import jakarta.persistence.*;

@Entity
@Table(name = "sales_report")
public class SalesReportJpaEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(name = "date", nullable = false)
  private LocalDate date;

  @Column(name = "product_id", nullable = false)
  private UUID productId;

  @Column(name = "product_name", nullable = false)
  private String productName;

  @Column(name = "quantity_sold", nullable = false)
  private int quantitySold;

  @Column(name = "total_sales", nullable = false)
  private BigDecimal totalSales;

  // Getters and setters
  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public LocalDate getDate() {
    return date;
  }

  public void setDate(LocalDate date) {
    this.date = date;
  }

  public UUID getProductId() {
    return productId;
  }

  public void setProductId(UUID productId) {
    this.productId = productId;
  }

  public String getProductName() {
    return productName;
  }

  public void setProductName(String productName) {
    this.productName = productName;
  }

  public int getQuantitySold() {
    return quantitySold;
  }

  public void setQuantitySold(int quantitySold) {
    this.quantitySold = quantitySold;
  }

  public BigDecimal getTotalSales() {
    return totalSales;
  }

  public void setTotalSales(BigDecimal totalSales) {
    this.totalSales = totalSales;
  }
}
