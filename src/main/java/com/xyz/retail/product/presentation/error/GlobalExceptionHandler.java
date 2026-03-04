/* Copyright 2026 XYZ Retail */
package com.xyz.retail.product.presentation.error;

import java.time.Instant;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.server.ResponseStatusException;

@ControllerAdvice
public class GlobalExceptionHandler {

  @ExceptionHandler(IllegalArgumentException.class)
  public ResponseEntity<RestErrorResponse> handleIllegalArgument(
      IllegalArgumentException ex, WebRequest req) {
    return ResponseEntity.badRequest()
        .body(
            new RestErrorResponse(
                Instant.now(),
                HttpStatus.BAD_REQUEST.value(),
                "Bad Request",
                ex.getMessage(),
                req.getDescription(false)));
  }

  @ExceptionHandler(Exception.class)
  public ResponseEntity<RestErrorResponse> handleGeneral(Exception ex, WebRequest req) {
    return ResponseEntity.internalServerError()
        .body(
            new RestErrorResponse(
                Instant.now(),
                HttpStatus.INTERNAL_SERVER_ERROR.value(),
                "Internal Server Error",
                ex.getMessage(),
                req.getDescription(false)));
  }

  @ExceptionHandler(ResponseStatusException.class)
  public ResponseEntity<RestErrorResponse> handleResponseStatusException(
      ResponseStatusException ex, WebRequest request) {

    RestErrorResponse errorResponse =
        new RestErrorResponse(
            Instant.now(),
            ex.getStatusCode().value(),
            ex.getStatusCode().toString(),
            ex.getReason(),
            request.getDescription(false));

    return new ResponseEntity<>(errorResponse, ex.getStatusCode());
  }
}
