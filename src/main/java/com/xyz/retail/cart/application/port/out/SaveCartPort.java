/* Copyright 2026 XYZ Retail */
package com.xyz.retail.cart.application.port.out;

import com.xyz.retail.cart.domain.entity.Cart;

public interface SaveCartPort {
  Cart saveCart(Cart cart);
}
