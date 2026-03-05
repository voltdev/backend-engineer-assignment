/* Copyright 2026 XYZ Retail */
package com.xyz.retail.cart.domain.exception;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class CartExceptionTest {

  @Test
  void shouldCreateCartExceptionWithMessage() {
    String message = "Cart error";
    CartException exception = new CartException(message);
    assertEquals(message, exception.getMessage());
  }

  @Test
  void shouldAllowNullMessage() {
    CartException exception = new CartException(null);
    assertNull(exception.getMessage());
  }
}
