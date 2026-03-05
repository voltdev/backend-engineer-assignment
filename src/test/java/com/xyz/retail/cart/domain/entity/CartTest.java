/* Copyright 2026 XYZ Retail */
package com.xyz.retail.cart.domain.entity;

import static org.junit.jupiter.api.Assertions.*;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

import org.junit.jupiter.api.Test;

import com.xyz.retail.cart.domain.valueobject.CartId;
import com.xyz.retail.cart.domain.valueobject.UserId;

class CartTest {

  @Test
  void shouldCreateEmptyCart() {
    CartId cartId = CartId.generate();
    UserId userId = new UserId("user1");

    Cart cart = new Cart(cartId, userId);

    assertEquals(cartId, cart.getId());
    assertEquals(userId, cart.getUserId());
    assertTrue(cart.getItems().isEmpty());
    assertEquals(BigDecimal.ZERO, cart.getTotalPrice());
  }

  @Test
  void shouldCreateCartWithItems() {
    CartId cartId = CartId.generate();
    UserId userId = new UserId("user1");
    List<CartItem> items = List.of();
    BigDecimal totalPrice = BigDecimal.ZERO;

    Cart cart = new Cart(cartId, userId, items, totalPrice);

    assertEquals(cartId, cart.getId());
    assertEquals(userId, cart.getUserId());
    assertEquals(items, cart.getItems());
    assertEquals(totalPrice, cart.getTotalPrice());
  }

  @Test
  void shouldAddNewItemToCart() {
    Cart cart = new Cart(CartId.generate(), new UserId("user1"));
    UUID productId = UUID.randomUUID();
    String productName = "Product";
    BigDecimal price = BigDecimal.valueOf(10.0);
    int quantity = 1;

    cart.addItem(productId, productName, price, quantity);

    assertEquals(1, cart.getItems().size());
    CartItem item = cart.getItems().get(0);
    assertEquals(productId, item.getProductId());
    assertEquals(productName, item.getProductName());
    assertEquals(price, item.getProductPrice());
    assertEquals(quantity, item.getQuantity());
    assertEquals(BigDecimal.valueOf(10.0), cart.getTotalPrice());
  }

  @Test
  void shouldIncreaseQuantityForExistingItem() {
    Cart cart = new Cart(CartId.generate(), new UserId("user1"));
    UUID productId = UUID.randomUUID();

    cart.addItem(productId, "Product", BigDecimal.valueOf(10.0), 1);
    cart.addItem(productId, "Product", BigDecimal.valueOf(10.0), 2);

    assertEquals(1, cart.getItems().size());
    CartItem item = cart.getItems().get(0);
    assertEquals(3, item.getQuantity());
    assertEquals(BigDecimal.valueOf(30.0), cart.getTotalPrice());
  }

  @Test
  void shouldReturnDefensiveCopyOfItems() {
    Cart cart = new Cart(CartId.generate(), new UserId("user1"));
    List<CartItem> items = cart.getItems();
    items.add(new CartItem(UUID.randomUUID(), UUID.randomUUID(), "name", BigDecimal.ONE, 1));

    assertTrue(cart.getItems().isEmpty()); // original not modified
  }
}
