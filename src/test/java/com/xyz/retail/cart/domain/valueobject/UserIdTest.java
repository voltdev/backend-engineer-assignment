/* Copyright 2026 XYZ Retail */
package com.xyz.retail.cart.domain.valueobject;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class UserIdTest {

  @Test
  void shouldCreateUserIdWithValue() {
    String value = "user123";
    UserId userId = new UserId(value);
    assertEquals(value, userId.value());
  }

  @Test
  void shouldAllowNullValue() {
    UserId userId = new UserId(null);
    assertNull(userId.value());
  }

  @Test
  void shouldBeEqualWhenSameValue() {
    UserId userId1 = new UserId("user");
    UserId userId2 = new UserId("user");
    assertEquals(userId1, userId2);
    assertEquals(userId1.hashCode(), userId2.hashCode());
  }

  @Test
  void shouldNotBeEqualWhenDifferentValue() {
    UserId userId1 = new UserId("user1");
    UserId userId2 = new UserId("user2");
    assertNotEquals(userId1, userId2);
  }
}
