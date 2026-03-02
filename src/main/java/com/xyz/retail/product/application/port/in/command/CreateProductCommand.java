package com.xyz.retail.product.application.port.in.command;

import java.math.BigDecimal;

public record CreateProductCommand(
        String name,
        BigDecimal price,
        int quantity
) {}