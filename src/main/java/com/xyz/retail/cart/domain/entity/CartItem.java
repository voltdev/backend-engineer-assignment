package com.xyz.retail.cart.domain.entity;

import java.math.BigDecimal;
import java.util.UUID;

public class CartItem {
    private final UUID id;
    private final UUID productId;
    private final String productName;
    private final BigDecimal productPrice;
    private int quantity;
    private BigDecimal subtotal;

    public CartItem(UUID id, UUID productId, String productName, BigDecimal productPrice, int quantity) {
        this.id = id;
        this.productId = productId;
        this.productName = productName;
        this.productPrice = productPrice;
        this.quantity = quantity;
        this.subtotal = productPrice.multiply(BigDecimal.valueOf(quantity));
    }

    public void increaseQuantity(int additionalQuantity) {
        this.quantity += additionalQuantity;
        recalculateSubtotal();
    }

    private void recalculateSubtotal() {
        this.subtotal = productPrice.multiply(BigDecimal.valueOf(quantity));
    }

    public UUID getId() {
        return id;
    }

    public UUID getProductId() {
        return productId;
    }

    public String getProductName() {
        return productName;
    }

    public BigDecimal getProductPrice() {
        return productPrice;
    }

    public int getQuantity() {
        return quantity;
    }

    public BigDecimal getSubtotal() {
        return subtotal;
    }
}