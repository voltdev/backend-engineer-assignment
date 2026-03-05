/* Copyright 2026 XYZ Retail */
package com.xyz.retail.order.domain.valueobject;

import static org.junit.jupiter.api.Assertions.*;

import java.util.UUID;

import org.junit.jupiter.api.Test;

class OrderIdTest {

  @Test
  void shouldCreateOrderIdWithValidUuid() {
    UUID uuid = UUID.randomUUID();
    OrderId orderId = new OrderId(uuid);
    assertEquals(uuid, orderId.value());
  }

  @Test
  void shouldGenerateNewOrderId() {
    OrderId orderId1 = OrderId.generate();
    OrderId orderId2 = OrderId.generate();
    assertNotNull(orderId1);
    assertNotNull(orderId2);
    assertNotEquals(orderId1, orderId2);
  }

  @Test
  void shouldBeEqualWhenSameUuid() {
    UUID uuid = UUID.randomUUID();
    OrderId orderId1 = new OrderId(uuid);
    OrderId orderId2 = new OrderId(uuid);
    assertEquals(orderId1, orderId2);
    assertEquals(orderId1.hashCode(), orderId2.hashCode());
  }
}
