/* Copyright 2026 XYZ Retail */
package com.xyz.retail.product.application.port.in.command;

import static org.junit.jupiter.api.Assertions.*;

import java.math.BigDecimal;

import org.junit.jupiter.api.Test;

class CreateProductCommandTest {

  @Test
  void shouldCreateCommandWithValidData() {
    String name = "Test Product";
    BigDecimal price = BigDecimal.valueOf(10.99);
    int quantity = 5;
    CreateProductCommand command = new CreateProductCommand(name, price, quantity);
    assertEquals(name, command.name());
    assertEquals(price, command.price());
    assertEquals(quantity, command.quantity());
  }

  @Test
  void shouldAllowNullName() {
    CreateProductCommand command = new CreateProductCommand(null, BigDecimal.ONE, 1);
    assertNull(command.name());
  }

  @Test
  void shouldAllowNullPrice() {
    CreateProductCommand command = new CreateProductCommand("name", null, 1);
    assertNull(command.price());
  }

  @Test
  void shouldBeEqualWhenSameValues() {
    CreateProductCommand cmd1 = new CreateProductCommand("name", BigDecimal.ONE, 1);
    CreateProductCommand cmd2 = new CreateProductCommand("name", BigDecimal.ONE, 1);
    assertEquals(cmd1, cmd2);
    assertEquals(cmd1.hashCode(), cmd2.hashCode());
  }

  @Test
  void shouldNotBeEqualWhenDifferentValues() {
    CreateProductCommand cmd1 = new CreateProductCommand("name1", BigDecimal.ONE, 1);
    CreateProductCommand cmd2 = new CreateProductCommand("name2", BigDecimal.ONE, 1);
    assertNotEquals(cmd1, cmd2);
  }
}
