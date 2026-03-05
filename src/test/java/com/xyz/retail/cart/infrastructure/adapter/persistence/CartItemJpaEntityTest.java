/* Copyright 2026 XYZ Retail */
package com.xyz.retail.cart.infrastructure.adapter.persistence;

import static org.junit.jupiter.api.Assertions.*;

import java.math.BigDecimal;
import java.util.UUID;

import org.junit.jupiter.api.Test;

class CartItemJpaEntityTest {

  @Test
  void shouldCreateCartItemJpaEntity() {
    UUID id = UUID.randomUUID();
    UUID productId = UUID.randomUUID();
    String productName = "Product";
    BigDecimal productPrice = BigDecimal.valueOf(10.0);
    int quantity = 2;
    BigDecimal subtotal = BigDecimal.valueOf(20.0);

    CartItemJpaEntity entity =
        new CartItemJpaEntity(id, productId, productName, productPrice, quantity, subtotal);

    assertEquals(id, entity.getId());
    assertEquals(productId, entity.getProductId());
    assertEquals(productName, entity.getProductName());
    assertEquals(productPrice, entity.getProductPrice());
    assertEquals(quantity, entity.getQuantity());
    assertEquals(subtotal, entity.getSubtotal());
  }

  @Test
  void shouldSetAndGetProperties() {
    CartItemJpaEntity entity = new CartItemJpaEntity();
    UUID id = UUID.randomUUID();
    CartJpaEntity cart = new CartJpaEntity();
    UUID productId = UUID.randomUUID();
    String productName = "Name";
    BigDecimal productPrice = BigDecimal.ONE;
    int quantity = 1;
    BigDecimal subtotal = BigDecimal.ONE;

    entity.setId(id);
    entity.setCart(cart);
    entity.setProductId(productId);
    entity.setProductName(productName);
    entity.setProductPrice(productPrice);
    entity.setQuantity(quantity);
    entity.setSubtotal(subtotal);

    assertEquals(id, entity.getId());
    assertEquals(cart, entity.getCart());
    assertEquals(productId, entity.getProductId());
    assertEquals(productName, entity.getProductName());
    assertEquals(productPrice, entity.getProductPrice());
    assertEquals(quantity, entity.getQuantity());
    assertEquals(subtotal, entity.getSubtotal());
  }
}
