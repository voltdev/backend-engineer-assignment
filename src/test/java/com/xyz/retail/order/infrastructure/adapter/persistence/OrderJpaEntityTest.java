/* Copyright 2026 XYZ Retail */
package com.xyz.retail.order.infrastructure.adapter.persistence;

import static org.junit.jupiter.api.Assertions.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

import org.junit.jupiter.api.Test;

class OrderJpaEntityTest {

  @Test
  void shouldCreateOrderJpaEntity() {
    UUID id = UUID.randomUUID();
    String userId = "user1";
    String customerName = "John Doe";
    String mobileNumber = "1234567890";
    BigDecimal totalAmount = BigDecimal.valueOf(100.0);
    LocalDateTime createdAt = LocalDateTime.now();

    OrderJpaEntity entity = new OrderJpaEntity();
    entity.setId(id);
    entity.setUserId(userId);
    entity.setCustomerName(customerName);
    entity.setMobileNumber(mobileNumber);
    entity.setTotalAmount(totalAmount);
    entity.setCreatedAt(createdAt);

    assertEquals(id, entity.getId());
    assertEquals(userId, entity.getUserId());
    assertEquals(customerName, entity.getCustomerName());
    assertEquals(mobileNumber, entity.getMobileNumber());
    assertEquals(totalAmount, entity.getTotalAmount());
    assertEquals(createdAt, entity.getCreatedAt());
  }

  @Test
  void shouldHandleNullValues() {
    OrderJpaEntity entity = new OrderJpaEntity();
    entity.setId(null);
    entity.setUserId(null);

    assertNull(entity.getId());
    assertNull(entity.getUserId());
  }

  @Test
  void shouldManageItems() {
    OrderJpaEntity entity = new OrderJpaEntity();
    OrderItemJpaEntity item = new OrderItemJpaEntity();
    item.setId(UUID.randomUUID());

    entity.addItem(item);

    assertEquals(1, entity.getItems().size());
  }
}
