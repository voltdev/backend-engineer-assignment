/* Copyright 2026 XYZ Retail */
package com.xyz.retail.product.domain.valueobject;

import java.util.UUID;

/** Strongly typed ID for Product aggregate. */
public record ProductId(UUID value) {

  public ProductId {
    if (value == null) {
      throw new IllegalArgumentException("ProductId cannot be null");
    }
  }

  public static ProductId newId() {
    return new ProductId(UUID.randomUUID());
  }

  @Override
  public String toString() {
    return value.toString();
  }
}
