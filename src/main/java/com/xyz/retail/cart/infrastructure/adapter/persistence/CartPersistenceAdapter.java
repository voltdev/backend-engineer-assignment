/* Copyright 2026 XYZ Retail */
package com.xyz.retail.cart.infrastructure.adapter.persistence;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.xyz.retail.cart.application.port.out.LoadCartPort;
import com.xyz.retail.cart.application.port.out.SaveCartPort;
import com.xyz.retail.cart.domain.entity.Cart;
import com.xyz.retail.cart.domain.entity.CartItem;
import com.xyz.retail.cart.domain.valueobject.CartId;
import com.xyz.retail.cart.domain.valueobject.UserId;

@Component
public class CartPersistenceAdapter implements LoadCartPort, SaveCartPort {

  private final CartJpaRepository cartRepository;

  public CartPersistenceAdapter(CartJpaRepository cartRepository) {
    this.cartRepository = cartRepository;
  }

  @Override
  public Optional<Cart> loadCartByUserId(UserId userId) {
    return cartRepository.findByUserId(userId.value()).map(this::mapToDomainEntity);
  }

  @Override
  @Transactional
  public Cart saveCart(Cart cart) {
    CartJpaEntity cartJpaEntity =
        cartRepository
            .findByUserId(cart.getUserId().value())
            .orElseGet(
                () -> {
                  CartJpaEntity newCart =
                      new CartJpaEntity(
                          cart.getId().value(), cart.getUserId().value(), cart.getTotalPrice());
                  return cartRepository.save(newCart);
                });

    cartJpaEntity.setTotalPrice(cart.getTotalPrice());

    // Clear existing items to avoid duplicates
    cartJpaEntity.getItems().clear();

    // Add all current items
    for (CartItem item : cart.getItems()) {
      CartItemJpaEntity itemEntity =
          new CartItemJpaEntity(
              item.getId(),
              item.getProductId(),
              item.getProductName(),
              item.getProductPrice(),
              item.getQuantity(),
              item.getSubtotal());
      cartJpaEntity.addItem(itemEntity);
    }

    CartJpaEntity savedCart = cartRepository.save(cartJpaEntity);
    return mapToDomainEntity(savedCart);
  }

  private Cart mapToDomainEntity(CartJpaEntity cartJpaEntity) {
    List<CartItem> items =
        cartJpaEntity.getItems().stream()
            .map(
                item ->
                    new CartItem(
                        item.getId(),
                        item.getProductId(),
                        item.getProductName(),
                        item.getProductPrice(),
                        item.getQuantity()))
            .collect(Collectors.toList());

    return new Cart(
        new CartId(cartJpaEntity.getId()),
        new UserId(cartJpaEntity.getUserId()),
        items,
        cartJpaEntity.getTotalPrice());
  }
}
