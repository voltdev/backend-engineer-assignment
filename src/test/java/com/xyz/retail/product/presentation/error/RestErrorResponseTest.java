/* Copyright 2026 XYZ Retail */
package com.xyz.retail.product.presentation.error;

import static org.junit.jupiter.api.Assertions.*;

import java.time.Instant;

import org.junit.jupiter.api.Test;

class RestErrorResponseTest {

  @Test
  void shouldCreateRestErrorResponse() {
    Instant timestamp = Instant.now();
    int status = 400;
    String error = "Bad Request";
    String message = "Error message";
    String path = "/test";

    RestErrorResponse response = new RestErrorResponse(timestamp, status, error, message, path);

    assertEquals(timestamp, response.timestamp());
    assertEquals(status, response.status());
    assertEquals(error, response.error());
    assertEquals(message, response.message());
    assertEquals(path, response.path());
  }

  @Test
  void shouldAllowNullValues() {
    RestErrorResponse response = new RestErrorResponse(null, 0, null, null, null);
    assertNull(response.timestamp());
    assertEquals(0, response.status());
    assertNull(response.error());
    assertNull(response.message());
    assertNull(response.path());
  }
}
