/* Copyright 2026 XYZ Retail */
package com.xyz.retail.order.presentation.dto;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class PlaceOrderRequestDtoTest {

  @Test
  void shouldCreatePlaceOrderRequestDto() {
    String customerName = "John Doe";
    String mobileNumber = "1234567890";

    PlaceOrderRequestDto dto = new PlaceOrderRequestDto(customerName, mobileNumber);

    assertEquals(customerName, dto.customerName());
    assertEquals(mobileNumber, dto.mobileNumber());
  }

  @Test
  void shouldAllowNullValues() {
    PlaceOrderRequestDto dto = new PlaceOrderRequestDto(null, null);
    assertNull(dto.customerName());
    assertNull(dto.mobileNumber());
  }

  @Test
  void shouldBeEqualWhenSameValues() {
    PlaceOrderRequestDto dto1 = new PlaceOrderRequestDto("John", "123");
    PlaceOrderRequestDto dto2 = new PlaceOrderRequestDto("John", "123");
    assertEquals(dto1, dto2);
  }
}
