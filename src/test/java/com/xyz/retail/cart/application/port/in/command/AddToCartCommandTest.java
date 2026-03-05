/* Copyright 2026 XYZ Retail */
package com.xyz.retail.cart.application.port.in.command;

import static org.junit.jupiter.api.Assertions.*;

import java.math.BigDecimal;
import java.util.UUID;

import org.junit.jupiter.api.Test;

class AddToCartCommandTest {

  @Test
  void shouldCreateAddToCartCommand() {
    String userId = "user1";
    UUID productId = UUID.randomUUID();
    String productName = "Product";
    BigDecimal productPrice = BigDecimal.valueOf(10.99);
    int quantity = 2;

    AddToCartCommand command =
        new AddToCartCommand(userId, productId, productName, productPrice, quantity);

    assertEquals(userId, command.userId());
    assertEquals(productId, command.productId());
    assertEquals(productName, command.productName());
    assertEquals(productPrice, command.productPrice());
    assertEquals(quantity, command.quantity());
  }

  @Test
  void shouldAllowNullValues() {
    AddToCartCommand command = new AddToCartCommand(null, null, null, null, 0);
    assertNull(command.userId());
    assertNull(command.productId());
    assertNull(command.productName());
    assertNull(command.productPrice());
    assertEquals(0, command.quantity());
  }

  @Test
  void shouldBeEqualWhenSameValues() {
    AddToCartCommand cmd1 =
        new AddToCartCommand("user", UUID.randomUUID(), "name", BigDecimal.ONE, 1);
    AddToCartCommand cmd2 =
        new AddToCartCommand("user", cmd1.productId(), "name", BigDecimal.ONE, 1);
    assertEquals(cmd1, cmd2);
  }
}
