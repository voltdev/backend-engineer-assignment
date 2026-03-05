/* Copyright 2026 XYZ Retail */
package com.xyz.retail.order.infrastructure.adapter.persistence;

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

import com.xyz.retail.order.domain.entity.Order;
import com.xyz.retail.order.domain.valueobject.OrderId;
import com.xyz.retail.order.domain.valueobject.OrderStatus;

class OrderPersistenceAdapterTest {

  @Mock private OrderJpaRepository orderRepository;

  private OrderPersistenceAdapter adapter;

  @BeforeEach
  void setUp() {
    MockitoAnnotations.openMocks(this);
    adapter = new OrderPersistenceAdapter(orderRepository);
  }

  @Test
  void shouldFindOrderById() {
    // Given
    UUID orderId = UUID.randomUUID();
    OrderJpaEntity entity = new OrderJpaEntity();
    entity.setId(orderId);
    entity.setUserId("user1");
    entity.setCustomerName("John");
    entity.setMobileNumber("123");
    entity.setTotalAmount(BigDecimal.TEN);
    entity.setCreatedAt(LocalDateTime.now());
    entity.setStatus(OrderJpaEntity.OrderStatusJpa.CREATED);

    when(orderRepository.findById(orderId)).thenReturn(Optional.of(entity));

    // When
    Optional<Order> result = adapter.findById(new OrderId(orderId));

    // Then
    assertTrue(result.isPresent());
    assertEquals(orderId, result.get().getId().value());
    verify(orderRepository).findById(orderId);
  }

  @Test
  void shouldReturnEmptyWhenOrderNotFound() {
    // Given
    UUID orderId = UUID.randomUUID();
    when(orderRepository.findById(orderId)).thenReturn(Optional.empty());

    // When
    Optional<Order> result = adapter.findById(new OrderId(orderId));

    // Then
    assertFalse(result.isPresent());
  }

  @Test
  void shouldFindOrdersByUserId() {
    // Given
    String userId = "user1";
    OrderJpaEntity entity = new OrderJpaEntity();
    entity.setId(UUID.randomUUID());
    entity.setUserId(userId);
    entity.setCustomerName("John");
    entity.setMobileNumber("123");
    entity.setTotalAmount(BigDecimal.TEN);
    entity.setCreatedAt(LocalDateTime.now());
    entity.setStatus(OrderJpaEntity.OrderStatusJpa.CREATED);

    when(orderRepository.findByUserIdOrderByCreatedAtDesc(userId))
        .thenReturn(java.util.List.of(entity));

    // When
    var result = adapter.findByUserId(userId);

    // Then
    assertEquals(1, result.size());
    verify(orderRepository).findByUserIdOrderByCreatedAtDesc(userId);
  }

  @Test
  void shouldSaveOrder() {
    // Given
    Order order =
        new Order(
            OrderId.generate(),
            "user1",
            "John Doe",
            "1234567890",
            new ArrayList<>(),
            BigDecimal.TEN,
            LocalDateTime.now(),
            OrderStatus.CREATED);

    OrderJpaEntity savedEntity = new OrderJpaEntity();
    savedEntity.setId(order.getId().value());
    savedEntity.setUserId("user1");
    savedEntity.setCustomerName("John Doe");
    savedEntity.setMobileNumber("1234567890");
    savedEntity.setTotalAmount(BigDecimal.TEN);
    savedEntity.setCreatedAt(order.getCreatedAt());
    savedEntity.setStatus(OrderJpaEntity.OrderStatusJpa.CREATED); // Set the status!

    when(orderRepository.save(any())).thenReturn(savedEntity);

    // When
    Order result = adapter.save(order);

    // Then
    assertNotNull(result);
    assertEquals(order.getId().value(), result.getId().value());
    assertEquals(order.getStatus(), result.getStatus());
    verify(orderRepository).save(any(OrderJpaEntity.class));
  }
}
