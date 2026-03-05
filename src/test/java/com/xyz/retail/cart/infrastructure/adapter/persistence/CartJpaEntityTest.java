/* Copyright 2026 XYZ Retail */
package com.xyz.retail.cart.infrastructure.adapter.persistence;

import static org.junit.jupiter.api.Assertions.*;

import java.math.BigDecimal;
import java.util.UUID;

import org.junit.jupiter.api.Test;

class CartJpaEntityTest {

  @Test
  void shouldCreateCartJpaEntity() {
    UUID id = UUID.randomUUID();
    String userId = "user1";
    BigDecimal totalPrice = BigDecimal.valueOf(10.0);

    CartJpaEntity entity = new CartJpaEntity(id, userId, totalPrice);

    assertEquals(id, entity.getId());
    assertEquals(userId, entity.getUserId());
    assertEquals(totalPrice, entity.getTotalPrice());
    assertTrue(entity.getItems().isEmpty());
  }

  @Test
  void shouldSetAndGetProperties() {
    CartJpaEntity entity = new CartJpaEntity();
    UUID id = UUID.randomUUID();
    String userId = "user2";
    BigDecimal totalPrice = BigDecimal.ONE;

    entity.setId(id);
    entity.setUserId(userId);
    entity.setTotalPrice(totalPrice);

    assertEquals(id, entity.getId());
    assertEquals(userId, entity.getUserId());
    assertEquals(totalPrice, entity.getTotalPrice());
  }

  @Test
  void shouldAddItem() {
    CartJpaEntity cart = new CartJpaEntity(UUID.randomUUID(), "user", BigDecimal.ZERO);
    CartItemJpaEntity item = new CartItemJpaEntity();
    item.setId(UUID.randomUUID());

    cart.addItem(item);

    assertEquals(1, cart.getItems().size());
    assertEquals(cart, item.getCart());
  }
}
