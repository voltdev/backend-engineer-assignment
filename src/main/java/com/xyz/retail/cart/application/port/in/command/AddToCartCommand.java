package com.xyz.retail.cart.application.port.in.command;

import java.math.BigDecimal;
import java.util.UUID;

public record AddToCartCommand(
        String userId,
        UUID productId,
        String productName,
        BigDecimal productPrice,
        int quantity
) {}