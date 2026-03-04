/* Copyright 2026 XYZ Retail */
package com.xyz.retail.order.domain.entity;

import java.math.BigDecimal;
import java.util.UUID;

public class OrderItem {
  private final UUID productId;
  private final String productName;
  private final BigDecimal productPrice;
  private final int quantity;
  private final BigDecimal subtotal;

  public OrderItem(
      UUID productId,
      String productName,
      BigDecimal productPrice,
      int quantity,
      BigDecimal subtotal) {
    this.productId = productId;
    this.productName = productName;
    this.productPrice = productPrice;
    this.quantity = quantity;
    this.subtotal = subtotal;
  }

  public UUID getProductId() {
    return productId;
  }

  public String getProductName() {
    return productName;
  }

  public BigDecimal getProductPrice() {
    return productPrice;
  }

  public int getQuantity() {
    return quantity;
  }

  public BigDecimal getSubtotal() {
    return subtotal;
  }
}
