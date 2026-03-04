/* Copyright 2026 XYZ Retail */
package com.xyz.retail.order.application.port.in;

import com.xyz.retail.order.application.port.in.command.PlaceOrderCommand;
import com.xyz.retail.order.domain.entity.Order;

public interface PlaceOrderUseCase {
  Order placeOrder(PlaceOrderCommand command);
}
