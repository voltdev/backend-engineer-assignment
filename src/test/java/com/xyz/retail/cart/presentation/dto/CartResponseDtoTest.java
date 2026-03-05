/* Copyright 2026 XYZ Retail */
package com.xyz.retail.cart.presentation.dto;

import static org.junit.jupiter.api.Assertions.*;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

import org.junit.jupiter.api.Test;

class CartResponseDtoTest {

  @Test
  void shouldCreateCartResponseDto() {
    UUID id = UUID.randomUUID();
    String userId = "user1";
    List<CartResponseDto.CartItemDto> items = List.of();
    BigDecimal totalPrice = BigDecimal.ZERO;

    CartResponseDto dto = new CartResponseDto(id, userId, items, totalPrice);

    assertEquals(id, dto.id());
    assertEquals(userId, dto.userId());
    assertEquals(items, dto.items());
    assertEquals(totalPrice, dto.totalPrice());
  }

  @Test
  void shouldCreateCartItemDto() {
    UUID id = UUID.randomUUID();
    UUID productId = UUID.randomUUID();
    String productName = "Product";
    BigDecimal productPrice = BigDecimal.valueOf(10.0);
    int quantity = 2;
    BigDecimal subtotal = BigDecimal.valueOf(20.0);

    CartResponseDto.CartItemDto itemDto =
        new CartResponseDto.CartItemDto(
            id, productId, productName, productPrice, quantity, subtotal);

    assertEquals(id, itemDto.id());
    assertEquals(productId, itemDto.productId());
    assertEquals(productName, itemDto.productName());
    assertEquals(productPrice, itemDto.productPrice());
    assertEquals(quantity, itemDto.quantity());
    assertEquals(subtotal, itemDto.subtotal());
  }

  @Test
  void shouldAllowNullValues() {
    CartResponseDto dto = new CartResponseDto(null, null, null, null);
    assertNull(dto.id());
    assertNull(dto.userId());
    assertNull(dto.items());
    assertNull(dto.totalPrice());
  }
}
