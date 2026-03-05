/* Copyright 2026 XYZ Retail */
package com.xyz.retail.product.presentation.dto;

import static org.junit.jupiter.api.Assertions.*;

import java.math.BigDecimal;
import java.util.UUID;

import org.junit.jupiter.api.Test;

class ProductResponseDtoTest {

  @Test
  void shouldCreateProductResponseDto() {
    UUID id = UUID.randomUUID();
    String name = "Test Product";
    BigDecimal price = BigDecimal.valueOf(10.99);
    int quantity = 5;
    boolean lowStock = false;

    ProductResponseDto dto = new ProductResponseDto(id, name, price, quantity, lowStock);

    assertEquals(id, dto.id());
    assertEquals(name, dto.name());
    assertEquals(price, dto.price());
    assertEquals(quantity, dto.quantity());
    assertEquals(lowStock, dto.lowStock());
  }

  @Test
  void shouldAllowNullValues() {
    ProductResponseDto dto = new ProductResponseDto(null, null, null, 0, true);
    assertNull(dto.id());
    assertNull(dto.name());
    assertNull(dto.price());
    assertEquals(0, dto.quantity());
    assertTrue(dto.lowStock());
  }
}
