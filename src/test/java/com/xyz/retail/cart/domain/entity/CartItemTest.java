/* Copyright 2026 XYZ Retail */
package com.xyz.retail.cart.domain.entity;

import static org.junit.jupiter.api.Assertions.*;

import java.math.BigDecimal;
import java.util.UUID;

import org.junit.jupiter.api.Test;

class CartItemTest {

  @Test
  void shouldCreateCartItem() {
    UUID id = UUID.randomUUID();
    UUID productId = UUID.randomUUID();
    String productName = "Test Product";
    BigDecimal productPrice = BigDecimal.valueOf(10.99);
    int quantity = 2;

    CartItem item = new CartItem(id, productId, productName, productPrice, quantity);

    assertEquals(id, item.getId());
    assertEquals(productId, item.getProductId());
    assertEquals(productName, item.getProductName());
    assertEquals(productPrice, item.getProductPrice());
    assertEquals(quantity, item.getQuantity());
    assertEquals(BigDecimal.valueOf(21.98), item.getSubtotal()); // 10.99 * 2
  }

  @Test
  void shouldIncreaseQuantity() {
    UUID id = UUID.randomUUID();
    UUID productId = UUID.randomUUID();
    BigDecimal price = BigDecimal.ONE;
    CartItem item = new CartItem(id, productId, "name", price, 1);

    item.increaseQuantity(2);

    assertEquals(3, item.getQuantity());
    assertEquals(BigDecimal.valueOf(3), item.getSubtotal());
  }

  @Test
  void shouldHandleZeroQuantity() {
    CartItem item = new CartItem(UUID.randomUUID(), UUID.randomUUID(), "name", BigDecimal.TEN, 0);
    assertEquals(0, item.getQuantity());
    assertEquals(BigDecimal.ZERO, item.getSubtotal());
  }
}
