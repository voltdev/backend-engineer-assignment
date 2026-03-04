/* Copyright 2026 XYZ Retail */
package com.xyz.retail.order.domain.valueobject;

import java.util.UUID;

public record OrderId(UUID value) {
  public static OrderId generate() {
    return new OrderId(UUID.randomUUID());
  }
}
