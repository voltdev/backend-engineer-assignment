/* Copyright 2026 XYZ Retail */
package com.xyz.retail.order.infrastructure.adapter.persistence;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.xyz.retail.order.application.port.out.LoadOrderPort;
import com.xyz.retail.order.application.port.out.SaveOrderPort;
import com.xyz.retail.order.domain.entity.Order;
import com.xyz.retail.order.domain.entity.OrderItem;
import com.xyz.retail.order.domain.valueobject.OrderId;
import com.xyz.retail.order.domain.valueobject.OrderStatus;

@Component
public class OrderPersistenceAdapter implements LoadOrderPort, SaveOrderPort {

  private final OrderJpaRepository orderRepository;

  public OrderPersistenceAdapter(OrderJpaRepository orderRepository) {
    this.orderRepository = orderRepository;
  }

  @Override
  public Optional<Order> findById(OrderId id) {
    return orderRepository.findById(id.value()).map(this::mapToDomainEntity);
  }

  @Override
  public List<Order> findByUserId(String userId) {
    return orderRepository.findByUserIdOrderByCreatedAtDesc(userId).stream()
        .map(this::mapToDomainEntity)
        .collect(Collectors.toList());
  }

  @Override
  @Transactional
  public Order save(Order order) {
    OrderJpaEntity orderEntity = new OrderJpaEntity();
    orderEntity.setId(order.getId().value());
    orderEntity.setUserId(order.getUserId());
    orderEntity.setCustomerName(order.getCustomerName());
    orderEntity.setMobileNumber(order.getMobileNumber());
    orderEntity.setTotalAmount(order.getTotalAmount());
    orderEntity.setCreatedAt(order.getCreatedAt());
    orderEntity.setStatus(mapToJpaStatus(order.getStatus()));

    // Clear existing items if any
    orderEntity.getItems().clear();

    // Add all order items
    for (OrderItem item : order.getItems()) {
      OrderItemJpaEntity itemEntity = new OrderItemJpaEntity();
      itemEntity.setProductId(item.getProductId());
      itemEntity.setProductName(item.getProductName());
      itemEntity.setProductPrice(item.getProductPrice());
      itemEntity.setQuantity(item.getQuantity());
      itemEntity.setSubtotal(item.getSubtotal());

      orderEntity.addItem(itemEntity);
    }

    OrderJpaEntity savedOrder = orderRepository.save(orderEntity);
    return mapToDomainEntity(savedOrder);
  }

  private Order mapToDomainEntity(OrderJpaEntity entity) {
    List<OrderItem> items =
        entity.getItems().stream()
            .map(
                item ->
                    new OrderItem(
                        item.getProductId(),
                        item.getProductName(),
                        item.getProductPrice(),
                        item.getQuantity(),
                        item.getSubtotal()))
            .collect(Collectors.toList());

    return new Order(
        new OrderId(entity.getId()),
        entity.getUserId(),
        entity.getCustomerName(),
        entity.getMobileNumber(),
        items,
        entity.getTotalAmount(),
        entity.getCreatedAt(),
        mapToDomainStatus(entity.getStatus()));
  }

  private OrderStatus mapToDomainStatus(OrderJpaEntity.OrderStatusJpa status) {
    if (status == null) {
      return OrderStatus.CREATED; // Default value or throw an exception
    }

    return switch (status) {
      case CREATED -> OrderStatus.CREATED;
      case CONFIRMED -> OrderStatus.CONFIRMED;
      case SHIPPED -> OrderStatus.SHIPPED;
      case DELIVERED -> OrderStatus.DELIVERED;
      case CANCELLED -> OrderStatus.CANCELLED;
    };
  }

  private OrderJpaEntity.OrderStatusJpa mapToJpaStatus(OrderStatus status) {
    return switch (status) {
      case CREATED -> OrderJpaEntity.OrderStatusJpa.CREATED;
      case CONFIRMED -> OrderJpaEntity.OrderStatusJpa.CONFIRMED;
      case SHIPPED -> OrderJpaEntity.OrderStatusJpa.SHIPPED;
      case DELIVERED -> OrderJpaEntity.OrderStatusJpa.DELIVERED;
      case CANCELLED -> OrderJpaEntity.OrderStatusJpa.CANCELLED;
    };
  }
}
