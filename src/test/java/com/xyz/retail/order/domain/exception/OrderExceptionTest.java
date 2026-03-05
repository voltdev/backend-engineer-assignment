/* Copyright 2026 XYZ Retail */
package com.xyz.retail.order.domain.exception;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class OrderExceptionTest {

  @Test
  void shouldCreateOrderExceptionWithMessage() {
    String message = "Order error";
    OrderException exception = new OrderException(message);
    assertEquals(message, exception.getMessage());
  }

  @Test
  void shouldAllowNullMessage() {
    OrderException exception = new OrderException(null);
    assertNull(exception.getMessage());
  }
}
