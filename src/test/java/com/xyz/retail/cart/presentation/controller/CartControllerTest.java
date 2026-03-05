/* Copyright 2026 XYZ Retail */
package com.xyz.retail.cart.presentation.controller;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

import java.math.BigDecimal;
import java.util.UUID;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.xyz.retail.cart.application.port.in.AddToCartUseCase;
import com.xyz.retail.cart.application.port.in.GetCartUseCase;
import com.xyz.retail.cart.domain.entity.Cart;
import com.xyz.retail.cart.domain.valueobject.CartId;
import com.xyz.retail.cart.domain.valueobject.UserId;
import com.xyz.retail.cart.presentation.dto.AddToCartRequestDto;
import com.xyz.retail.cart.presentation.dto.CartResponseDto;

class CartControllerTest {

  @Mock private AddToCartUseCase addToCartUseCase;

  @Mock private GetCartUseCase getCartUseCase;

  private CartController controller;

  @BeforeEach
  void setUp() {
    MockitoAnnotations.openMocks(this);
    controller = new CartController(addToCartUseCase, getCartUseCase);
  }

  @Test
  void shouldAddToCart() {
    // Given
    String userId = "user1";
    UUID productId = UUID.randomUUID();
    AddToCartRequestDto dto =
        new AddToCartRequestDto(productId, "Product", BigDecimal.valueOf(10.0), 1);
    Cart cart = new Cart(CartId.generate(), new UserId(userId));
    when(addToCartUseCase.addToCart(any())).thenReturn(cart);

    // When
    CartResponseDto result = controller.addToCart(userId, dto);

    // Then
    assertNotNull(result);
    verify(addToCartUseCase).addToCart(any());
  }

  @Test
  void shouldGetCart() {
    // Given
    String userId = "user1";
    Cart cart = new Cart(CartId.generate(), new UserId(userId));
    when(getCartUseCase.getCart(any())).thenReturn(cart);

    // When
    CartResponseDto result = controller.getCart(userId);

    // Then
    assertNotNull(result);
    assertEquals(userId, result.userId());
    verify(getCartUseCase).getCart(any());
  }
}
