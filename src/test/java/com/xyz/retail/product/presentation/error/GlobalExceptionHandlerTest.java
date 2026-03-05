/* Copyright 2026 XYZ Retail */
package com.xyz.retail.product.presentation.error;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.server.ResponseStatusException;

class GlobalExceptionHandlerTest {

  private final GlobalExceptionHandler handler = new GlobalExceptionHandler();

  @Test
  void shouldHandleIllegalArgumentException() {
    // Given
    IllegalArgumentException ex = new IllegalArgumentException("Invalid argument");
    WebRequest req = mock(WebRequest.class);
    when(req.getDescription(false)).thenReturn("uri=/test");

    // When
    ResponseEntity<RestErrorResponse> response = handler.handleIllegalArgument(ex, req);

    // Then
    assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
    assertNotNull(response.getBody());
    assertEquals("Invalid argument", response.getBody().message());
  }

  @Test
  void shouldHandleGeneralException() {
    // Given
    Exception ex = new RuntimeException("General error");
    WebRequest req = mock(WebRequest.class);
    when(req.getDescription(false)).thenReturn("uri=/test");

    // When
    ResponseEntity<RestErrorResponse> response = handler.handleGeneral(ex, req);

    // Then
    assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
    assertNotNull(response.getBody());
    assertEquals("General error", response.getBody().message());
  }

  @Test
  void shouldHandleResponseStatusException() {
    // Given
    ResponseStatusException ex = new ResponseStatusException(HttpStatus.NOT_FOUND, "Not found");
    WebRequest req = mock(WebRequest.class);
    when(req.getDescription(false)).thenReturn("uri=/test");

    // When
    ResponseEntity<RestErrorResponse> response = handler.handleResponseStatusException(ex, req);

    // Then
    assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    assertNotNull(response.getBody());
    assertEquals("Not found", response.getBody().message());
  }
}
