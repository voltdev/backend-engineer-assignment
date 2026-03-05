/* Copyright 2026 XYZ Retail */
package com.xyz.retail.product.infrastructure.entity;

import static org.junit.jupiter.api.Assertions.*;

import java.math.BigDecimal;
import java.util.UUID;

import org.junit.jupiter.api.Test;

class JpaProductEntityTest {

  @Test
  void shouldCreateJpaProductEntity() {
    UUID id = UUID.randomUUID();
    String name = "Test Product";
    BigDecimal price = BigDecimal.valueOf(10.99);
    int quantity = 5;

    JpaProductEntity entity = new JpaProductEntity(id, name, price, quantity);

    assertEquals(id, entity.getId());
    assertEquals(name, entity.getName());
    assertEquals(price, entity.getPrice());
    assertEquals(quantity, entity.getQuantity());
  }

  @Test
  void shouldAllowNullValuesInConstructor() {
    JpaProductEntity entity = new JpaProductEntity(null, null, null, 0);
    assertNull(entity.getId());
    assertNull(entity.getName());
    assertNull(entity.getPrice());
    assertEquals(0, entity.getQuantity());
  }
}
