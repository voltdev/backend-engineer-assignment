/* Copyright 2026 XYZ Retail */
package com.xyz.retail.cart.infrastructure.adapter.persistence;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import jakarta.persistence.*;

@Entity
@Table(name = "carts")
public class CartJpaEntity {

  @Id
  @Column(name = "id")
  private UUID id;

  @Column(name = "user_id", nullable = false)
  private String userId;

  @Column(name = "total_price", nullable = false)
  private BigDecimal totalPrice;

  @OneToMany(mappedBy = "cart", cascade = CascadeType.ALL, orphanRemoval = true)
  private List<CartItemJpaEntity> items = new ArrayList<>();

  public CartJpaEntity() {}

  public CartJpaEntity(UUID id, String userId, BigDecimal totalPrice) {
    this.id = id;
    this.userId = userId;
    this.totalPrice = totalPrice;
  }

  public UUID getId() {
    return id;
  }

  public void setId(UUID id) {
    this.id = id;
  }

  public String getUserId() {
    return userId;
  }

  public void setUserId(String userId) {
    this.userId = userId;
  }

  public BigDecimal getTotalPrice() {
    return totalPrice;
  }

  public void setTotalPrice(BigDecimal totalPrice) {
    this.totalPrice = totalPrice;
  }

  public List<CartItemJpaEntity> getItems() {
    return items;
  }

  public void setItems(List<CartItemJpaEntity> items) {
    this.items = items;
  }

  public void addItem(CartItemJpaEntity item) {
    items.add(item);
    item.setCart(this);
  }
}
