/* Copyright 2026 XYZ Retail */
package com.xyz.retail.product.presentation.mapper;

import static org.junit.jupiter.api.Assertions.*;

import java.math.BigDecimal;

import org.junit.jupiter.api.Test;

import com.xyz.retail.product.application.port.in.command.CreateProductCommand;
import com.xyz.retail.product.domain.valueobject.ProductId;
import com.xyz.retail.product.presentation.dto.ProductRequestDto;
import com.xyz.retail.product.presentation.dto.ProductResponseDto;
import com.xyz.retail.product.service.domain.entity.Product;

class ProductApiMapperTest {

  @Test
  void shouldMapRequestDtoToCommand() {
    // Given
    ProductRequestDto dto = new ProductRequestDto("Test", BigDecimal.ONE, 1);

    // When
    CreateProductCommand command = ProductApiMapper.toCommand(dto);

    // Then
    assertEquals("Test", command.name());
    assertEquals(BigDecimal.ONE, command.price());
    assertEquals(1, command.quantity());
  }

  @Test
  void shouldMapProductToResponseDto() {
    // Given
    ProductId productId = ProductId.newId();
    Product product = new Product(productId, "Test", BigDecimal.ONE, 1);
    // Assuming isLowStock is false for quantity > 0

    // When
    ProductResponseDto response = ProductApiMapper.toResponse(product);

    // Then
    assertEquals(productId.value(), response.id());
    assertEquals("Test", response.name());
    assertEquals(BigDecimal.ONE, response.price());
    assertEquals(1, response.quantity());
    // assertFalse(response.lowStock()); // assuming
  }
}
