/* Copyright 2026 XYZ Retail */
package com.xyz.retail.cart.application.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

import java.math.BigDecimal;
import java.util.Optional;
import java.util.UUID;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.xyz.retail.cart.application.port.in.command.AddToCartCommand;
import com.xyz.retail.cart.application.port.in.query.GetCartQuery;
import com.xyz.retail.cart.application.port.out.DeleteCartPort;
import com.xyz.retail.cart.application.port.out.LoadCartPort;
import com.xyz.retail.cart.application.port.out.SaveCartPort;
import com.xyz.retail.cart.domain.entity.Cart;
import com.xyz.retail.cart.domain.exception.CartException;
import com.xyz.retail.cart.domain.valueobject.CartId;
import com.xyz.retail.cart.domain.valueobject.UserId;
import com.xyz.retail.product.application.port.out.LoadProductPort;
import com.xyz.retail.product.domain.valueobject.ProductId;
import com.xyz.retail.product.service.domain.entity.Product;

class CartServiceTest {

  @Mock private LoadCartPort loadCartPort;

  @Mock private SaveCartPort saveCartPort;

  @Mock private LoadProductPort loadProductPort;

  @Mock private DeleteCartPort deleteCartPort;

  private CartService cartService;

  @BeforeEach
  void setUp() {
    MockitoAnnotations.openMocks(this);
    cartService = new CartService(loadCartPort, saveCartPort, loadProductPort, deleteCartPort);
  }

  @Test
  void shouldAddToCartWhenProductExistsAndEnoughStock() {
    // Given
    UUID productId = UUID.randomUUID();
    Product product = new Product(new ProductId(productId), "Product", BigDecimal.valueOf(10.0), 5);
    AddToCartCommand command =
        new AddToCartCommand("user1", productId, "Product", BigDecimal.valueOf(10.0), 2);
    Cart cart = new Cart(CartId.generate(), new UserId("user1"));
    when(loadProductPort.loadProductById(productId)).thenReturn(Optional.of(product));
    when(loadCartPort.loadCartByUserId(any())).thenReturn(Optional.of(cart));
    when(saveCartPort.saveCart(any())).thenReturn(cart);

    // When
    Cart result = cartService.addToCart(command);

    // Then
    assertNotNull(result);
    verify(loadProductPort).loadProductById(productId);
    verify(saveCartPort).saveCart(any());
  }

  @Test
  void shouldThrowExceptionWhenProductNotFound() {
    // Given
    UUID productId = UUID.randomUUID();
    AddToCartCommand command =
        new AddToCartCommand("user1", productId, "Product", BigDecimal.ONE, 1);
    when(loadProductPort.loadProductById(productId)).thenReturn(Optional.empty());

    // When & Then
    assertThrows(CartException.class, () -> cartService.addToCart(command));
  }

  @Test
  void shouldThrowExceptionWhenNotEnoughStock() {
    // Given
    UUID productId = UUID.randomUUID();
    Product product = new Product(new ProductId(productId), "Product", BigDecimal.ONE, 1);
    AddToCartCommand command =
        new AddToCartCommand("user1", productId, "Product", BigDecimal.ONE, 2);
    when(loadProductPort.loadProductById(productId)).thenReturn(Optional.of(product));

    // When & Then
    assertThrows(CartException.class, () -> cartService.addToCart(command));
  }

  @Test
  void shouldGetCartWhenExists() {
    // Given
    GetCartQuery query = new GetCartQuery("user1");
    Cart cart = new Cart(CartId.generate(), new UserId("user1"));
    when(loadCartPort.loadCartByUserId(any())).thenReturn(Optional.of(cart));

    // When
    Cart result = cartService.getCart(query);

    // Then
    assertEquals(cart, result);
  }

  @Test
  void shouldThrowExceptionWhenCartNotFound() {
    // Given
    GetCartQuery query = new GetCartQuery("user1");
    when(loadCartPort.loadCartByUserId(any())).thenReturn(Optional.empty());

    // When & Then
    assertThrows(CartException.class, () -> cartService.getCart(query));
  }

  @Test
  void shouldClearCart() {
    // Given
    String userId = "user1";

    // When
    cartService.clearCart(userId);

    // Then
    verify(deleteCartPort).deleteByUserId(any(UserId.class));
  }
}
