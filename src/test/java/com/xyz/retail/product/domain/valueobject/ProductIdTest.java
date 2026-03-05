/* Copyright 2026 XYZ Retail */
package com.xyz.retail.product.domain.valueobject;

import static org.junit.jupiter.api.Assertions.*;

import java.util.UUID;

import org.junit.jupiter.api.Test;

class ProductIdTest {

  @Test
  void shouldCreateProductIdWithValidUuid() {
    UUID uuid = UUID.randomUUID();
    ProductId productId = new ProductId(uuid);
    assertEquals(uuid, productId.value());
  }

  @Test
  void shouldThrowExceptionWhenUuidIsNull() {
    assertThrows(IllegalArgumentException.class, () -> new ProductId(null));
  }

  @Test
  void shouldGenerateNewId() {
    ProductId productId1 = ProductId.newId();
    ProductId productId2 = ProductId.newId();
    assertNotNull(productId1);
    assertNotNull(productId2);
    assertNotEquals(productId1, productId2);
  }

  @Test
  void shouldReturnStringRepresentation() {
    UUID uuid = UUID.randomUUID();
    ProductId productId = new ProductId(uuid);
    assertEquals(uuid.toString(), productId.toString());
  }

  @Test
  void shouldBeEqualWhenSameUuid() {
    UUID uuid = UUID.randomUUID();
    ProductId productId1 = new ProductId(uuid);
    ProductId productId2 = new ProductId(uuid);
    assertEquals(productId1, productId2);
    assertEquals(productId1.hashCode(), productId2.hashCode());
  }

  @Test
  void shouldNotBeEqualWhenDifferentUuid() {
    ProductId productId1 = ProductId.newId();
    ProductId productId2 = ProductId.newId();
    assertNotEquals(productId1, productId2);
  }
}
