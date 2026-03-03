package com.xyz.retail.cart.application.service;

import com.xyz.retail.cart.application.port.in.AddToCartUseCase;
import com.xyz.retail.cart.application.port.in.GetCartUseCase;
import com.xyz.retail.cart.application.port.in.command.AddToCartCommand;
import com.xyz.retail.cart.application.port.in.query.GetCartQuery;
import com.xyz.retail.cart.application.port.out.LoadCartPort;
import com.xyz.retail.cart.application.port.out.SaveCartPort;
import com.xyz.retail.cart.domain.entity.Cart;
import com.xyz.retail.cart.domain.exception.CartException;

import java.util.UUID;

public class CartService implements AddToCartUseCase, GetCartUseCase {

    private final LoadCartPort loadCartPort;
    private final SaveCartPort saveCartPort;

    public CartService(LoadCartPort loadCartPort, SaveCartPort saveCartPort) {
        this.loadCartPort = loadCartPort;
        this.saveCartPort = saveCartPort;
    }

    @Override
    public Cart addToCart(AddToCartCommand command) {
        Cart cart = loadCartPort.loadCartByUserId(command.userId())
                .orElseGet(() -> new Cart(UUID.randomUUID(), command.userId()));

        cart.addItem(
                command.productId(),
                command.productName(),
                command.productPrice(),
                command.quantity()
        );

        return saveCartPort.saveCart(cart);
    }

    @Override
    public Cart getCart(GetCartQuery query) {
        return loadCartPort.loadCartByUserId(query.userId())
                .orElseThrow(() -> new CartException("Cart not found for user: " + query.userId()));
    }
}