/* Copyright 2026 XYZ Retail */
package com.xyz.retail.order.presentation.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

public record OrderResponseDto(
    UUID id,
    String userId,
    String customerName,
    String mobileNumber,
    List<OrderItemDto> items,
    BigDecimal totalAmount,
    LocalDateTime createdAt,
    String status) {
  public record OrderItemDto(
      UUID productId,
      String productName,
      BigDecimal productPrice,
      int quantity,
      BigDecimal subtotal) {}
}
