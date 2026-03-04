/* Copyright 2026 XYZ Retail */
package com.xyz.retail.product.service.domain.entity;

import java.math.BigDecimal;
import java.util.Objects;

import com.xyz.retail.product.domain.valueobject.ProductId;

/**
 * Domain Aggregate Root: Product
 *
 * <p>This is a pure domain object containing business rules. No annotations from JPA, Spring, etc.
 */
public class Product {

  private static final int LOW_STOCK_THRESHOLD = 10;

  private final ProductId id;
  private final String name;
  private final BigDecimal price;
  private final int quantity;

  public Product(ProductId id, String name, BigDecimal price, int quantity) {
    validate(name, price, quantity);
    this.id = id;
    this.name = name;
    this.price = price;
    this.quantity = quantity;
  }

  // Business validation
  private void validate(String name, BigDecimal price, int quantity) {
    if (name == null || name.isBlank()) {
      throw new IllegalArgumentException("Product name cannot be empty");
    }
    if (price == null || price.compareTo(BigDecimal.ZERO) <= 0) {
      throw new IllegalArgumentException("Product price must be positive");
    }
    if (quantity < 0) {
      throw new IllegalArgumentException("Product quantity cannot be negative");
    }
  }

  // Business behavior
  public boolean isLowStock() {
    return quantity < LOW_STOCK_THRESHOLD;
  }

  // Getters (no setters to maintain immutability)
  public ProductId getId() {
    return id;
  }

  public String getName() {
    return name;
  }

  public BigDecimal getPrice() {
    return price;
  }

  public int getQuantity() {
    return quantity;
  }

  // Domain logic: decrease stock and return new immutable Product
  public Product reduceStock(int amount) {
    if (amount <= 0) {
      throw new IllegalArgumentException("Reduction amount must be > 0");
    }
    if (amount > this.quantity) {
      throw new IllegalArgumentException("Not enough stock available");
    }
    return new Product(id, name, price, quantity - amount);
  }

  // Domain logic: increase stock
  public Product increaseStock(int amount) {
    if (amount <= 0) {
      throw new IllegalArgumentException("Increase amount must be > 0");
    }
    return new Product(id, name, price, quantity + amount);
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (!(o instanceof Product product)) return false;
    return Objects.equals(id, product.id);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id);
  }
}
