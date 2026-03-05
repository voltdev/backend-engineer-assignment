/* Copyright 2026 XYZ Retail */
package com.xyz.retail.product.presentation.controller;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class PingControllerTest {

  private final PingController controller = new PingController();

  @Test
  void shouldReturnPong() {
    String result = controller.ping();
    assertEquals("pong", result);
  }
}
