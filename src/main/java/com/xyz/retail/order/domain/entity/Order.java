/* Copyright 2026 XYZ Retail */
package com.xyz.retail.order.domain.entity;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import com.xyz.retail.cart.domain.entity.CartItem;
import com.xyz.retail.order.domain.valueobject.OrderId;
import com.xyz.retail.order.domain.valueobject.OrderStatus;

public class Order {
  private final OrderId id;
  private final String userId;
  private final String customerName;
  private final String mobileNumber;
  private final List<OrderItem> items;
  private final BigDecimal totalAmount;
  private final LocalDateTime createdAt;
  private OrderStatus status;

  public Order(
      OrderId id,
      String userId,
      String customerName,
      String mobileNumber,
      List<OrderItem> items,
      BigDecimal totalAmount,
      LocalDateTime createdAt,
      OrderStatus status) {
    this.id = id;
    this.userId = userId;
    this.customerName = customerName;
    this.mobileNumber = mobileNumber;
    this.items = items;
    this.totalAmount = totalAmount;
    this.createdAt = createdAt;
    this.status = status;
  }

  public static Order createFromCart(
      String userId, String customerName, String mobileNumber, List<CartItem> cartItems) {

    List<OrderItem> orderItems =
        cartItems.stream()
            .map(
                cartItem ->
                    new OrderItem(
                        cartItem.getProductId(),
                        cartItem.getProductName(),
                        cartItem.getProductPrice(),
                        cartItem.getQuantity(),
                        cartItem.getSubtotal()))
            .toList();

    BigDecimal totalAmount =
        orderItems.stream().map(OrderItem::getSubtotal).reduce(BigDecimal.ZERO, BigDecimal::add);

    return new Order(
        OrderId.generate(),
        userId,
        customerName,
        mobileNumber,
        orderItems,
        totalAmount,
        LocalDateTime.now(),
        OrderStatus.CREATED);
  }

  public OrderId getId() {
    return id;
  }

  public String getUserId() {
    return userId;
  }

  public String getCustomerName() {
    return customerName;
  }

  public String getMobileNumber() {
    return mobileNumber;
  }

  public List<OrderItem> getItems() {
    return new ArrayList<>(items);
  }

  public BigDecimal getTotalAmount() {
    return totalAmount;
  }

  public LocalDateTime getCreatedAt() {
    return createdAt;
  }

  public OrderStatus getStatus() {
    return status;
  }

  public void setStatus(OrderStatus status) {
    this.status = status;
  }
}
