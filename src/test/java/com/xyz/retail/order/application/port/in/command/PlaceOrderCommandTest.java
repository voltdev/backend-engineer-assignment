/* Copyright 2026 XYZ Retail */
package com.xyz.retail.order.application.port.in.command;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class PlaceOrderCommandTest {

  @Test
  void shouldCreatePlaceOrderCommand() {
    String userId = "user1";
    String customerName = "John Doe";
    String mobileNumber = "1234567890";

    PlaceOrderCommand command = new PlaceOrderCommand(userId, customerName, mobileNumber);

    assertEquals(userId, command.userId());
    assertEquals(customerName, command.customerName());
    assertEquals(mobileNumber, command.mobileNumber());
  }

  @Test
  void shouldAllowNullValues() {
    PlaceOrderCommand command = new PlaceOrderCommand(null, null, null);
    assertNull(command.userId());
    assertNull(command.customerName());
    assertNull(command.mobileNumber());
  }

  @Test
  void shouldBeEqualWhenSameValues() {
    PlaceOrderCommand cmd1 = new PlaceOrderCommand("user", "John", "123");
    PlaceOrderCommand cmd2 = new PlaceOrderCommand("user", "John", "123");
    assertEquals(cmd1, cmd2);
  }
}
