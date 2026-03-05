/* Copyright 2026 XYZ Retail */
package com.xyz.retail.order.presentation.mapper;

import static org.junit.jupiter.api.Assertions.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.junit.jupiter.api.Test;

import com.xyz.retail.order.application.port.in.command.PlaceOrderCommand;
import com.xyz.retail.order.domain.entity.Order;
import com.xyz.retail.order.domain.entity.OrderItem;
import com.xyz.retail.order.domain.valueobject.OrderId;
import com.xyz.retail.order.domain.valueobject.OrderStatus;
import com.xyz.retail.order.presentation.dto.OrderResponseDto;
import com.xyz.retail.order.presentation.dto.PlaceOrderRequestDto;

class OrderApiMapperTest {

  @Test
  void shouldMapPlaceOrderRequestDtoToCommand() {
    // Given
    String userId = "user1";
    PlaceOrderRequestDto dto = new PlaceOrderRequestDto("John Doe", "1234567890");

    // When
    PlaceOrderCommand command = OrderApiMapper.toCommand(userId, dto);

    // Then
    assertEquals(userId, command.userId());
    assertEquals("John Doe", command.customerName());
    assertEquals("1234567890", command.mobileNumber());
  }

  @Test
  void shouldMapOrderToResponseDto() {
    // Given
    List<OrderItem> items = new ArrayList<>();
    items.add(
        new OrderItem(UUID.randomUUID(), "Product", BigDecimal.TEN, 2, BigDecimal.valueOf(20)));
    Order order =
        new Order(
            OrderId.generate(),
            "user1",
            "John Doe",
            "1234567890",
            items,
            BigDecimal.valueOf(20),
            LocalDateTime.now(),
            OrderStatus.CREATED);

    // When
    OrderResponseDto response = OrderApiMapper.toResponse(order);

    // Then
    assertEquals(order.getId().value(), response.id());
    assertEquals("user1", response.userId());
    assertEquals("John Doe", response.customerName());
    assertEquals("1234567890", response.mobileNumber());
    assertEquals(1, response.items().size());
    assertEquals(BigDecimal.valueOf(20), response.totalAmount());
    assertEquals("CREATED", response.status());
  }
}
