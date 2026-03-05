/* Copyright 2026 XYZ Retail */
package com.xyz.retail.cart.domain.valueobject;

import static org.junit.jupiter.api.Assertions.*;

import java.util.UUID;

import org.junit.jupiter.api.Test;

class CartIdTest {

  @Test
  void shouldCreateCartIdWithValidUuid() {
    UUID uuid = UUID.randomUUID();
    CartId cartId = new CartId(uuid);
    assertEquals(uuid, cartId.value());
  }

  @Test
  void shouldGenerateNewCartId() {
    CartId cartId1 = CartId.generate();
    CartId cartId2 = CartId.generate();
    assertNotNull(cartId1);
    assertNotNull(cartId2);
    assertNotEquals(cartId1, cartId2);
  }

  @Test
  void shouldBeEqualWhenSameUuid() {
    UUID uuid = UUID.randomUUID();
    CartId cartId1 = new CartId(uuid);
    CartId cartId2 = new CartId(uuid);
    assertEquals(cartId1, cartId2);
    assertEquals(cartId1.hashCode(), cartId2.hashCode());
  }

  @Test
  void shouldNotBeEqualWhenDifferentUuid() {
    CartId cartId1 = CartId.generate();
    CartId cartId2 = CartId.generate();
    assertNotEquals(cartId1, cartId2);
  }
}
