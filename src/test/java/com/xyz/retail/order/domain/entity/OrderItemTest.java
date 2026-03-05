/* Copyright 2026 XYZ Retail */
package com.xyz.retail.order.domain.entity;

import static org.junit.jupiter.api.Assertions.*;

import java.math.BigDecimal;
import java.util.UUID;

import org.junit.jupiter.api.Test;

class OrderItemTest {

  @Test
  void shouldCreateOrderItem() {
    UUID productId = UUID.randomUUID();
    String productName = "Product";
    BigDecimal productPrice = BigDecimal.valueOf(10.0);
    int quantity = 2;
    BigDecimal subtotal = BigDecimal.valueOf(20.0);

    OrderItem item = new OrderItem(productId, productName, productPrice, quantity, subtotal);

    assertEquals(productId, item.getProductId());
    assertEquals(productName, item.getProductName());
    assertEquals(productPrice, item.getProductPrice());
    assertEquals(quantity, item.getQuantity());
    assertEquals(subtotal, item.getSubtotal());
  }

  @Test
  void shouldAllowNullValues() {
    OrderItem item = new OrderItem(null, null, null, 0, null);
    assertNull(item.getProductId());
    assertNull(item.getProductName());
    assertNull(item.getProductPrice());
    assertEquals(0, item.getQuantity());
    assertNull(item.getSubtotal());
  }
}
