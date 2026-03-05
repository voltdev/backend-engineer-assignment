/* Copyright 2026 XYZ Retail */
package com.xyz.retail.order.domain.valueobject;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class OrderStatusTest {

  @Test
  void shouldHaveAllOrderStatuses() {
    assertNotNull(OrderStatus.CREATED);
    assertNotNull(OrderStatus.CONFIRMED);
    assertNotNull(OrderStatus.SHIPPED);
    assertNotNull(OrderStatus.DELIVERED);
    assertNotNull(OrderStatus.CANCELLED);
  }

  @Test
  void shouldHaveCorrectNumberOfStatuses() {
    OrderStatus[] statuses = OrderStatus.values();
    assertEquals(5, statuses.length);
  }
}
