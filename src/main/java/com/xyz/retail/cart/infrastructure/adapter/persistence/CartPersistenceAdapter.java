package com.xyz.retail.cart.infrastructure.adapter.persistence;

import com.xyz.retail.cart.application.port.out.LoadCartPort;
import com.xyz.retail.cart.application.port.out.SaveCartPort;
import com.xyz.retail.cart.domain.entity.Cart;
import com.xyz.retail.cart.domain.entity.CartItem;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.stream.Collectors;
import java.util.ArrayList;
import java.util.List;

@Component
public class CartPersistenceAdapter implements LoadCartPort, SaveCartPort {

    private final CartJpaRepository cartRepository;
    private final CartItemJpaRepository cartItemRepository;

    public CartPersistenceAdapter(CartJpaRepository cartRepository, CartItemJpaRepository cartItemRepository) {
        this.cartRepository = cartRepository;
        this.cartItemRepository = cartItemRepository;
    }

    @Override
    public Optional<Cart> loadCartByUserId(String userId) {
        return cartRepository.findByUserId(userId)
                .map(this::mapToDomainEntity);
    }

    @Override
    @Transactional
    public Cart saveCart(Cart cart) {
        CartJpaEntity cartJpaEntity = cartRepository.findByUserId(cart.getUserId())
                .orElseGet(() -> {
                    CartJpaEntity newCart = new CartJpaEntity(
                            cart.getId(),
                            cart.getUserId(),
                            cart.getTotalPrice()
                    );
                    return cartRepository.save(newCart);
                });

        cartJpaEntity.setTotalPrice(cart.getTotalPrice());

        // Clear existing items to avoid duplicates
        cartJpaEntity.getItems().clear();

        // Add all current items
        for (CartItem item : cart.getItems()) {
            CartItemJpaEntity itemEntity = new CartItemJpaEntity(
                    item.getId(),
                    item.getProductId(),
                    item.getProductName(),
                    item.getProductPrice(),
                    item.getQuantity(),
                    item.getSubtotal()
            );
            cartJpaEntity.addItem(itemEntity);
        }

        CartJpaEntity savedCart = cartRepository.save(cartJpaEntity);
        return mapToDomainEntity(savedCart);
    }

    private Cart mapToDomainEntity(CartJpaEntity cartJpaEntity) {
        List<CartItem> items = cartJpaEntity.getItems().stream()
                .map(item -> new CartItem(
                        item.getId(),
                        item.getProductId(),
                        item.getProductName(),
                        item.getProductPrice(),
                        item.getQuantity()
                ))
                .collect(Collectors.toList());

        return new Cart(
                cartJpaEntity.getId(),
                cartJpaEntity.getUserId(),
                items,
                cartJpaEntity.getTotalPrice()
        );
    }
}