/* Copyright 2026 XYZ Retail */
package com.xyz.retail.order.application.port.in.query;

import static org.junit.jupiter.api.Assertions.*;

import java.util.UUID;

import org.junit.jupiter.api.Test;

class OrderSearchQueryTest {

  @Test
  void shouldCreateOrderSearchQuery() {
    UUID orderId = UUID.randomUUID();
    OrderSearchQuery query = new OrderSearchQuery(orderId);
    assertEquals(orderId, query.orderId());
  }

  @Test
  void shouldAllowNullOrderId() {
    OrderSearchQuery query = new OrderSearchQuery(null);
    assertNull(query.orderId());
  }

  @Test
  void shouldBeEqualWhenSameOrderId() {
    UUID orderId = UUID.randomUUID();
    OrderSearchQuery q1 = new OrderSearchQuery(orderId);
    OrderSearchQuery q2 = new OrderSearchQuery(orderId);
    assertEquals(q1, q2);
  }

  @Test
  void shouldNotBeEqualWhenDifferentOrderId() {
    OrderSearchQuery q1 = new OrderSearchQuery(UUID.randomUUID());
    OrderSearchQuery q2 = new OrderSearchQuery(UUID.randomUUID());
    assertNotEquals(q1, q2);
  }
}
