/* Copyright 2026 XYZ Retail */
package com.xyz.retail.order.infrastructure.adapter.persistence;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import jakarta.persistence.*;

@Entity
@Table(name = "orders")
public class OrderJpaEntity {

  @Id
  @Column(name = "id")
  private UUID id;

  @Column(name = "user_id", nullable = false)
  private String userId;

  @Column(name = "customer_name", nullable = false)
  private String customerName;

  @Column(name = "mobile_number", nullable = false)
  private String mobileNumber;

  @Column(name = "total_amount", nullable = false)
  private BigDecimal totalAmount;

  @Column(name = "created_at", nullable = false)
  private LocalDateTime createdAt;

  @Column(name = "status", nullable = false)
  @Enumerated(EnumType.STRING)
  private OrderStatusJpa status;

  @OneToMany(mappedBy = "order", cascade = CascadeType.ALL, orphanRemoval = true)
  private List<OrderItemJpaEntity> items = new ArrayList<>();

  public OrderJpaEntity() {}

  public UUID getId() {
    return id;
  }

  public void setId(UUID id) {
    this.id = id;
  }

  public String getUserId() {
    return userId;
  }

  public void setUserId(String userId) {
    this.userId = userId;
  }

  public String getCustomerName() {
    return customerName;
  }

  public void setCustomerName(String customerName) {
    this.customerName = customerName;
  }

  public String getMobileNumber() {
    return mobileNumber;
  }

  public void setMobileNumber(String mobileNumber) {
    this.mobileNumber = mobileNumber;
  }

  public BigDecimal getTotalAmount() {
    return totalAmount;
  }

  public void setTotalAmount(BigDecimal totalAmount) {
    this.totalAmount = totalAmount;
  }

  public LocalDateTime getCreatedAt() {
    return createdAt;
  }

  public void setCreatedAt(LocalDateTime createdAt) {
    this.createdAt = createdAt;
  }

  public OrderStatusJpa getStatus() {
    return status;
  }

  public void setStatus(OrderStatusJpa status) {
    this.status = status;
  }

  public List<OrderItemJpaEntity> getItems() {
    return items;
  }

  public void setItems(List<OrderItemJpaEntity> items) {
    this.items = items;
  }

  public void addItem(OrderItemJpaEntity item) {
    items.add(item);
    item.setOrder(this);
  }

  public enum OrderStatusJpa {
    CREATED,
    CONFIRMED,
    SHIPPED,
    DELIVERED,
    CANCELLED
  }
}
