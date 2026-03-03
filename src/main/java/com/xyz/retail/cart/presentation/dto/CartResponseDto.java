package com.xyz.retail.cart.presentation.dto;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

public record CartResponseDto(
        UUID id,
        String userId,
        List<CartItemDto> items,
        BigDecimal totalPrice
) {
    public record CartItemDto(
            UUID id,
            UUID productId,
            String productName,
            BigDecimal productPrice,
            int quantity,
            BigDecimal subtotal
    ) {}
}