/* Copyright 2026 XYZ Retail */
package com.xyz.retail.cart.presentation.dto;

import static org.junit.jupiter.api.Assertions.*;

import java.math.BigDecimal;
import java.util.UUID;

import org.junit.jupiter.api.Test;

class AddToCartRequestDtoTest {

  @Test
  void shouldCreateAddToCartRequestDto() {
    UUID productId = UUID.randomUUID();
    String productName = "Product";
    BigDecimal productPrice = BigDecimal.valueOf(10.99);
    Integer quantity = 2;

    AddToCartRequestDto dto =
        new AddToCartRequestDto(productId, productName, productPrice, quantity);

    assertEquals(productId, dto.productId());
    assertEquals(productName, dto.productName());
    assertEquals(productPrice, dto.productPrice());
    assertEquals(quantity, dto.quantity());
  }

  @Test
  void shouldAllowNullValues() {
    AddToCartRequestDto dto = new AddToCartRequestDto(null, null, null, null);
    assertNull(dto.productId());
    assertNull(dto.productName());
    assertNull(dto.productPrice());
    assertNull(dto.quantity());
  }
}
