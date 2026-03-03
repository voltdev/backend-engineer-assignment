package com.xyz.retail.cart.application.port.in;

import com.xyz.retail.cart.application.port.in.query.GetCartQuery;
import com.xyz.retail.cart.domain.entity.Cart;

public interface GetCartUseCase {
    Cart getCart(GetCartQuery query);
}