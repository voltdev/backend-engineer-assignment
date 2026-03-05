/* Copyright 2026 XYZ Retail */
package com.xyz.retail.order.infrastructure.adapter.persistence;

import static org.junit.jupiter.api.Assertions.*;

import java.math.BigDecimal;
import java.util.UUID;

import org.junit.jupiter.api.Test;

class OrderItemJpaEntityTest {

  @Test
  void shouldCreateOrderItemJpaEntity() {
    UUID productId = UUID.randomUUID();
    String productName = "Product";
    BigDecimal productPrice = BigDecimal.valueOf(10.0);
    int quantity = 2;
    BigDecimal subtotal = BigDecimal.valueOf(20.0);

    OrderItemJpaEntity entity = new OrderItemJpaEntity();
    entity.setProductId(productId);
    entity.setProductName(productName);
    entity.setProductPrice(productPrice);
    entity.setQuantity(quantity);
    entity.setSubtotal(subtotal);

    assertEquals(productId, entity.getProductId());
    assertEquals(productName, entity.getProductName());
    assertEquals(productPrice, entity.getProductPrice());
    assertEquals(quantity, entity.getQuantity());
    assertEquals(subtotal, entity.getSubtotal());
  }

  @Test
  void shouldSetAndGetId() {
    OrderItemJpaEntity entity = new OrderItemJpaEntity();
    UUID id = UUID.randomUUID();

    entity.setId(id);

    assertEquals(id, entity.getId());
  }

  @Test
  void shouldSetAndGetOrder() {
    OrderItemJpaEntity entity = new OrderItemJpaEntity();
    OrderJpaEntity order = new OrderJpaEntity();

    entity.setOrder(order);

    assertEquals(order, entity.getOrder());
  }
}
