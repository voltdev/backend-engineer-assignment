/* Copyright 2026 XYZ Retail */
package com.xyz.retail.cart.domain.entity;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import com.xyz.retail.cart.domain.valueobject.CartId;
import com.xyz.retail.cart.domain.valueobject.UserId;

public class Cart {
  private final CartId id;
  private final UserId userId;
  private final List<CartItem> items;
  private BigDecimal totalPrice;

  public Cart(CartId id, UserId userId) {
    this.id = id;
    this.userId = userId;
    this.items = new ArrayList<>();
    this.totalPrice = BigDecimal.ZERO;
  }

  public Cart(CartId id, UserId userId, List<CartItem> items, BigDecimal totalPrice) {
    this.id = id;
    this.userId = userId;
    this.items = items;
    this.totalPrice = totalPrice;
  }

  public void addItem(UUID productId, String productName, BigDecimal productPrice, int quantity) {
    Optional<CartItem> existingItem =
        items.stream().filter(item -> item.getProductId().equals(productId)).findFirst();

    if (existingItem.isPresent()) {
      CartItem item = existingItem.get();
      item.increaseQuantity(quantity);
    } else {
      items.add(new CartItem(UUID.randomUUID(), productId, productName, productPrice, quantity));
    }

    recalculateTotalPrice();
  }

  private void recalculateTotalPrice() {
    this.totalPrice =
        items.stream().map(CartItem::getSubtotal).reduce(BigDecimal.ZERO, BigDecimal::add);
  }

  public CartId getId() {
    return id;
  }

  public UserId getUserId() {
    return userId;
  }

  public List<CartItem> getItems() {
    return new ArrayList<>(items);
  }

  public BigDecimal getTotalPrice() {
    return totalPrice;
  }
}
