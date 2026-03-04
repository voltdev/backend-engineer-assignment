/* Copyright 2026 XYZ Retail */
package com.xyz.retail.order.application.port.out;

import com.xyz.retail.order.domain.entity.Order;

public interface SaveOrderPort {
  Order save(Order order);
}
