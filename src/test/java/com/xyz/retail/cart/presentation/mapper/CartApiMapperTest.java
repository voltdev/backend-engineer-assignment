/* Copyright 2026 XYZ Retail */
package com.xyz.retail.cart.presentation.mapper;

import static org.junit.jupiter.api.Assertions.*;

import java.math.BigDecimal;
import java.util.UUID;

import org.junit.jupiter.api.Test;

import com.xyz.retail.cart.application.port.in.command.AddToCartCommand;
import com.xyz.retail.cart.domain.entity.Cart;
import com.xyz.retail.cart.domain.valueobject.CartId;
import com.xyz.retail.cart.domain.valueobject.UserId;
import com.xyz.retail.cart.presentation.dto.AddToCartRequestDto;
import com.xyz.retail.cart.presentation.dto.CartResponseDto;

class CartApiMapperTest {

  @Test
  void shouldMapRequestDtoToCommand() {
    // Given
    String userId = "user1";
    UUID productId = UUID.randomUUID();
    AddToCartRequestDto dto =
        new AddToCartRequestDto(productId, "Product", BigDecimal.valueOf(10.0), 2);

    // When
    AddToCartCommand command = CartApiMapper.toCommand(userId, dto);

    // Then
    assertEquals(userId, command.userId());
    assertEquals(productId, command.productId());
    assertEquals("Product", command.productName());
    assertEquals(BigDecimal.valueOf(10.0), command.productPrice());
    assertEquals(2, command.quantity());
  }

  @Test
  void shouldMapCartToResponse() {
    // Given
    Cart cart = new Cart(CartId.generate(), new UserId("user1"));
    UUID productId = UUID.randomUUID();
    cart.addItem(productId, "Product", BigDecimal.valueOf(10.0), 1);

    // When
    CartResponseDto response = CartApiMapper.toResponse(cart);

    // Then
    assertEquals(cart.getId().value(), response.id());
    assertEquals("user1", response.userId());
    assertEquals(cart.getTotalPrice(), response.totalPrice());
    assertEquals(1, response.items().size());
  }
}
