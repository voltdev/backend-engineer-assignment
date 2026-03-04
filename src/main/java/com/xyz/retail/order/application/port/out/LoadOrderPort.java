/* Copyright 2026 XYZ Retail */
package com.xyz.retail.order.application.port.out;

import java.util.List;
import java.util.Optional;

import com.xyz.retail.order.domain.entity.Order;
import com.xyz.retail.order.domain.valueobject.OrderId;

public interface LoadOrderPort {
  Optional<Order> findById(OrderId id);

  List<Order> findByUserId(String userId);
}
