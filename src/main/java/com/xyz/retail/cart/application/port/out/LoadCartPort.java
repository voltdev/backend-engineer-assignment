package com.xyz.retail.cart.application.port.out;

import com.xyz.retail.cart.domain.entity.Cart;

import java.util.Optional;

public interface LoadCartPort {
    Optional<Cart> loadCartByUserId(String userId);
}
