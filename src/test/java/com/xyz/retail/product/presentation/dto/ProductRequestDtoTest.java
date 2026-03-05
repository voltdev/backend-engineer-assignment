/* Copyright 2026 XYZ Retail */
package com.xyz.retail.product.presentation.dto;

import static org.junit.jupiter.api.Assertions.*;

import java.math.BigDecimal;

import org.junit.jupiter.api.Test;

class ProductRequestDtoTest {

  @Test
  void shouldCreateProductRequestDto() {
    String name = "Test Product";
    BigDecimal price = BigDecimal.valueOf(10.99);
    int quantity = 5;

    ProductRequestDto dto = new ProductRequestDto(name, price, quantity);

    assertEquals(name, dto.name());
    assertEquals(price, dto.price());
    assertEquals(quantity, dto.quantity());
  }

  @Test
  void shouldAllowNullValues() {
    ProductRequestDto dto = new ProductRequestDto(null, null, -1);
    assertNull(dto.name());
    assertNull(dto.price());
    assertEquals(-1, dto.quantity());
  }
}
