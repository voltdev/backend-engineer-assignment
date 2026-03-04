/* Copyright 2026 XYZ Retail */
package com.xyz.retail.order.application.port.in;

import java.util.Optional;

import com.xyz.retail.order.application.port.in.query.OrderSearchQuery;
import com.xyz.retail.order.domain.entity.Order;

public interface OrderSearchUseCase {
  Optional<Order> findOrderById(OrderSearchQuery query);
}
