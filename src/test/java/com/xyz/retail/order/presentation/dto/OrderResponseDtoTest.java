/* Copyright 2026 XYZ Retail */
package com.xyz.retail.order.presentation.dto;

import static org.junit.jupiter.api.Assertions.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import org.junit.jupiter.api.Test;

class OrderResponseDtoTest {

  @Test
  void shouldCreateOrderResponseDto() {
    UUID id = UUID.randomUUID();
    String userId = "user1";
    String customerName = "John Doe";
    String mobileNumber = "1234567890";
    List<OrderResponseDto.OrderItemDto> items = List.of();
    BigDecimal totalAmount = BigDecimal.valueOf(100.0);
    LocalDateTime createdAt = LocalDateTime.now();
    String status = "CREATED";

    OrderResponseDto dto =
        new OrderResponseDto(
            id, userId, customerName, mobileNumber, items, totalAmount, createdAt, status);

    assertEquals(id, dto.id());
    assertEquals(userId, dto.userId());
    assertEquals(customerName, dto.customerName());
    assertEquals(mobileNumber, dto.mobileNumber());
    assertEquals(items, dto.items());
    assertEquals(totalAmount, dto.totalAmount());
    assertEquals(createdAt, dto.createdAt());
    assertEquals(status, dto.status());
  }

  @Test
  void shouldCreateOrderItemDto() {
    UUID productId = UUID.randomUUID();
    String productName = "Product";
    BigDecimal productPrice = BigDecimal.valueOf(10.0);
    int quantity = 2;
    BigDecimal subtotal = BigDecimal.valueOf(20.0);

    OrderResponseDto.OrderItemDto itemDto =
        new OrderResponseDto.OrderItemDto(productId, productName, productPrice, quantity, subtotal);

    assertEquals(productId, itemDto.productId());
    assertEquals(productName, itemDto.productName());
    assertEquals(productPrice, itemDto.productPrice());
    assertEquals(quantity, itemDto.quantity());
    assertEquals(subtotal, itemDto.subtotal());
  }

  @Test
  void shouldAllowNullValues() {
    OrderResponseDto dto = new OrderResponseDto(null, null, null, null, null, null, null, null);
    assertNull(dto.id());
    assertNull(dto.userId());
    assertNull(dto.items());
  }
}
