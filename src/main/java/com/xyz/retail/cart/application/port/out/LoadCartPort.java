/* Copyright 2026 XYZ Retail */
package com.xyz.retail.cart.application.port.out;

import java.util.Optional;

import com.xyz.retail.cart.domain.entity.Cart;
import com.xyz.retail.cart.domain.valueobject.UserId;

public interface LoadCartPort {
  Optional<Cart> loadCartByUserId(UserId userId);
}
