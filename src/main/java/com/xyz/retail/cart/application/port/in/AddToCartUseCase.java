package com.xyz.retail.cart.application.port.in;

import com.xyz.retail.cart.application.port.in.command.AddToCartCommand;
import com.xyz.retail.cart.domain.entity.Cart;

public interface AddToCartUseCase {
    Cart addToCart(AddToCartCommand command);
}