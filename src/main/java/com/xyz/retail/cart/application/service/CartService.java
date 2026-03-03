/* Copyright 2026 XYZ Retail */
package com.xyz.retail.cart.application.service;

import com.xyz.retail.cart.application.port.in.AddToCartUseCase;
import com.xyz.retail.cart.application.port.in.GetCartUseCase;
import com.xyz.retail.cart.application.port.in.command.AddToCartCommand;
import com.xyz.retail.cart.application.port.in.query.GetCartQuery;
import com.xyz.retail.cart.application.port.out.LoadCartPort;
import com.xyz.retail.cart.application.port.out.SaveCartPort;
import com.xyz.retail.cart.domain.entity.Cart;
import com.xyz.retail.cart.domain.exception.CartException;
import com.xyz.retail.cart.domain.valueobject.CartId;
import com.xyz.retail.cart.domain.valueobject.UserId;
import com.xyz.retail.product.application.port.out.LoadProductPort;
import com.xyz.retail.product.service.domain.entity.Product;

public class CartService implements AddToCartUseCase, GetCartUseCase {

  private final LoadCartPort loadCartPort;
  private final SaveCartPort saveCartPort;
  private final LoadProductPort loadProductPort;

  public CartService(
      LoadCartPort loadCartPort, SaveCartPort saveCartPort, LoadProductPort loadProductPort) {
    this.loadCartPort = loadCartPort;
    this.saveCartPort = saveCartPort;
    this.loadProductPort = loadProductPort;
  }

  @Override
  public Cart addToCart(AddToCartCommand command) {
    // Verify product exists and get latest information
    Product product =
        loadProductPort
            .loadProductById(command.productId())
            .orElseThrow(() -> new CartException("Product not found: " + command.productId()));

    // Check if product has enough stock
    if (product.getQuantity() < command.quantity()) {
      throw new CartException("Not enough stock for product: " + product.getName());
    }

    UserId userId = new UserId(command.userId());

    Cart cart =
        loadCartPort.loadCartByUserId(userId).orElseGet(() -> new Cart(CartId.generate(), userId));

    cart.addItem(
        product.getId().value(), product.getName(), product.getPrice(), command.quantity());

    return saveCartPort.saveCart(cart);
  }

  @Override
  public Cart getCart(GetCartQuery query) {
    UserId userId = new UserId(query.userId());

    return loadCartPort
        .loadCartByUserId(userId)
        .orElseThrow(() -> new CartException("Cart not found for user: " + query.userId()));
  }
}
