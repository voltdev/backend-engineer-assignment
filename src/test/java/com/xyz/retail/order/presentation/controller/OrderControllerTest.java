/* Copyright 2026 XYZ Retail */
package com.xyz.retail.order.presentation.controller;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Optional;
import java.util.UUID;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.web.server.ResponseStatusException;

import com.xyz.retail.order.application.port.in.OrderSearchUseCase;
import com.xyz.retail.order.application.port.in.PlaceOrderUseCase;
import com.xyz.retail.order.domain.entity.Order;
import com.xyz.retail.order.domain.exception.OrderException;
import com.xyz.retail.order.domain.valueobject.OrderId;
import com.xyz.retail.order.domain.valueobject.OrderStatus;
import com.xyz.retail.order.presentation.dto.OrderResponseDto;
import com.xyz.retail.order.presentation.dto.PlaceOrderRequestDto;

class OrderControllerTest {

  @Mock private PlaceOrderUseCase placeOrderUseCase;

  @Mock private OrderSearchUseCase orderSearchUseCase;

  private OrderController controller;

  @BeforeEach
  void setUp() {
    MockitoAnnotations.openMocks(this);
    controller = new OrderController(placeOrderUseCase, orderSearchUseCase);
  }

  @Test
  void shouldPlaceOrder() {
    // Given
    String userId = "user1";
    PlaceOrderRequestDto dto = new PlaceOrderRequestDto("John Doe", "1234567890");
    Order order =
        new Order(
            OrderId.generate(),
            userId,
            "John Doe",
            "1234567890",
            new ArrayList<>(),
            BigDecimal.TEN,
            LocalDateTime.now(),
            OrderStatus.CREATED);
    when(placeOrderUseCase.placeOrder(any())).thenReturn(order);

    // When
    OrderResponseDto result = controller.placeOrder(userId, dto);

    // Then
    assertNotNull(result);
    verify(placeOrderUseCase).placeOrder(any());
  }

  @Test
  void shouldThrowExceptionWhenPlaceOrderFails() {
    // Given
    String userId = "user1";
    PlaceOrderRequestDto dto = new PlaceOrderRequestDto("John Doe", "1234567890");
    when(placeOrderUseCase.placeOrder(any())).thenThrow(new OrderException("Order failed"));

    // When & Then
    assertThrows(ResponseStatusException.class, () -> controller.placeOrder(userId, dto));
  }

  @Test
  void shouldSearchOrderById() {
    // Given
    UUID orderId = UUID.randomUUID();
    Order order =
        new Order(
            new OrderId(orderId),
            "user1",
            "John Doe",
            "1234567890",
            new ArrayList<>(),
            BigDecimal.TEN,
            LocalDateTime.now(),
            OrderStatus.CREATED);
    when(orderSearchUseCase.findOrderById(any())).thenReturn(Optional.of(order));

    // When
    OrderResponseDto result = controller.searchOrderById(orderId);

    // Then
    assertNotNull(result);
    assertEquals(orderId, result.id());
    verify(orderSearchUseCase).findOrderById(any());
  }

  @Test
  void shouldThrowExceptionWhenOrderNotFound() {
    // Given
    UUID orderId = UUID.randomUUID();
    when(orderSearchUseCase.findOrderById(any())).thenReturn(Optional.empty());

    // When & Then
    assertThrows(ResponseStatusException.class, () -> controller.searchOrderById(orderId));
  }
}
