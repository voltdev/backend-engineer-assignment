/* Copyright 2026 XYZ Retail */
package com.xyz.retail.cart.domain.valueobject;

import java.util.UUID;

public record CartId(UUID value) {
  public static CartId generate() {
    return new CartId(UUID.randomUUID());
  }
}
