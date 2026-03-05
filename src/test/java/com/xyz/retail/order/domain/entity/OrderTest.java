/* Copyright 2026 XYZ Retail */
package com.xyz.retail.order.domain.entity;

import static org.junit.jupiter.api.Assertions.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.junit.jupiter.api.Test;

import com.xyz.retail.cart.domain.entity.CartItem;
import com.xyz.retail.order.domain.valueobject.OrderId;
import com.xyz.retail.order.domain.valueobject.OrderStatus;

class OrderTest {

  @Test
  void shouldCreateOrder() {
    OrderId orderId = OrderId.generate();
    String userId = "user1";
    String customerName = "John Doe";
    String mobileNumber = "1234567890";
    List<OrderItem> items = new ArrayList<>();
    BigDecimal totalAmount = BigDecimal.ZERO;
    LocalDateTime createdAt = LocalDateTime.now();
    OrderStatus status = OrderStatus.CREATED;

    Order order =
        new Order(
            orderId, userId, customerName, mobileNumber, items, totalAmount, createdAt, status);

    assertEquals(orderId, order.getId());
    assertEquals(userId, order.getUserId());
    assertEquals(customerName, order.getCustomerName());
    assertEquals(mobileNumber, order.getMobileNumber());
    assertEquals(items, order.getItems());
    assertEquals(totalAmount, order.getTotalAmount());
    assertEquals(createdAt, order.getCreatedAt());
    assertEquals(status, order.getStatus());
  }

  @Test
  void shouldSetOrderStatus() {
    Order order =
        new Order(
            OrderId.generate(),
            "user",
            "name",
            "123",
            new ArrayList<>(),
            BigDecimal.ZERO,
            LocalDateTime.now(),
            OrderStatus.CREATED);

    order.setStatus(OrderStatus.CONFIRMED);

    assertEquals(OrderStatus.CONFIRMED, order.getStatus());
  }

  @Test
  void shouldCreateOrderFromCart() {
    String userId = "user1";
    String customerName = "John";
    String mobileNumber = "123";
    List<CartItem> cartItems = new ArrayList<>();
    cartItems.add(new CartItem(UUID.randomUUID(), UUID.randomUUID(), "Product", BigDecimal.TEN, 2));

    Order order = Order.createFromCart(userId, customerName, mobileNumber, cartItems);

    assertEquals(userId, order.getUserId());
    assertEquals(customerName, order.getCustomerName());
    assertEquals(mobileNumber, order.getMobileNumber());
    assertEquals(1, order.getItems().size());
    assertEquals(BigDecimal.valueOf(20), order.getTotalAmount());
    assertEquals(OrderStatus.CREATED, order.getStatus());
    assertNotNull(order.getCreatedAt());
  }
}
