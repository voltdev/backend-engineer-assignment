/* Copyright 2026 XYZ Retail */
package com.xyz.retail.cart.infrastructure.adapter.persistence;

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

import com.xyz.retail.cart.domain.entity.Cart;
import com.xyz.retail.cart.domain.valueobject.CartId;
import com.xyz.retail.cart.domain.valueobject.UserId;

class CartPersistenceAdapterTest {

  @Mock private CartJpaRepository cartRepository;

  private CartPersistenceAdapter adapter;

  @BeforeEach
  void setUp() {
    MockitoAnnotations.openMocks(this);
    adapter = new CartPersistenceAdapter(cartRepository);
  }

  @Test
  void shouldLoadCartByUserIdWhenExists() {
    // Given
    UserId userId = new UserId("user1");
    CartJpaEntity entity = new CartJpaEntity(UUID.randomUUID(), "user1", BigDecimal.ZERO);
    when(cartRepository.findByUserId("user1")).thenReturn(Optional.of(entity));

    // When
    Optional<Cart> cart = adapter.loadCartByUserId(userId);

    // Then
    assertTrue(cart.isPresent());
    assertEquals(new CartId(entity.getId()), cart.get().getId());
    verify(cartRepository).findByUserId("user1");
  }

  @Test
  void shouldReturnEmptyWhenCartNotFound() {
    // Given
    UserId userId = new UserId("user1");
    when(cartRepository.findByUserId("user1")).thenReturn(Optional.empty());

    // When
    Optional<Cart> cart = adapter.loadCartByUserId(userId);

    // Then
    assertFalse(cart.isPresent());
  }

  @Test
  void shouldSaveCart() {
    // Given
    Cart cart = new Cart(CartId.generate(), new UserId("user1"));
    cart.addItem(UUID.randomUUID(), "Product", BigDecimal.ONE, 1);
    CartJpaEntity savedEntity =
        new CartJpaEntity(cart.getId().value(), "user1", cart.getTotalPrice());
    when(cartRepository.findByUserId("user1")).thenReturn(Optional.of(savedEntity));
    when(cartRepository.save(any())).thenReturn(savedEntity);

    // When
    Cart result = adapter.saveCart(cart);

    // Then
    assertNotNull(result);
    verify(cartRepository).save(any());
  }

  @Test
  void shouldDeleteByUserId() {
    // Given
    UserId userId = new UserId("user1");
    CartJpaEntity entity = new CartJpaEntity(UUID.randomUUID(), "user1", BigDecimal.ZERO);
    when(cartRepository.findByUserId("user1")).thenReturn(Optional.of(entity));

    // When
    adapter.deleteByUserId(userId);

    // Then
    verify(cartRepository).delete(entity);
  }
}
