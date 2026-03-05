/* Copyright 2026 XYZ Retail */
package com.xyz.retail.cart.application.port.in.query;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class GetCartQueryTest {

  @Test
  void shouldCreateGetCartQuery() {
    String userId = "user1";
    GetCartQuery query = new GetCartQuery(userId);
    assertEquals(userId, query.userId());
  }

  @Test
  void shouldAllowNullUserId() {
    GetCartQuery query = new GetCartQuery(null);
    assertNull(query.userId());
  }

  @Test
  void shouldBeEqualWhenSameUserId() {
    GetCartQuery q1 = new GetCartQuery("user");
    GetCartQuery q2 = new GetCartQuery("user");
    assertEquals(q1, q2);
  }
}
