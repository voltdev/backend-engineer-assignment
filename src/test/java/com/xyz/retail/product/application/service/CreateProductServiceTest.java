/* Copyright 2026 XYZ Retail */
package com.xyz.retail.product.application.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

import java.math.BigDecimal;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.xyz.retail.product.application.port.in.command.CreateProductCommand;
import com.xyz.retail.product.application.port.out.SaveProductPort;
import com.xyz.retail.product.domain.valueobject.ProductId;
import com.xyz.retail.product.service.domain.entity.Product;

class CreateProductServiceTest {

  @Mock private SaveProductPort saveProductPort;

  private CreateProductService createProductService;

  @BeforeEach
  void setUp() {
    MockitoAnnotations.openMocks(this);
    createProductService = new CreateProductService(saveProductPort);
  }

  @Test
  void shouldCreateProductSuccessfully() {
    // Given
    CreateProductCommand command =
        new CreateProductCommand("Test Product", BigDecimal.valueOf(10.99), 5);
    Product savedProduct =
        new Product(ProductId.newId(), "Test Product", BigDecimal.valueOf(10.99), 5);
    when(saveProductPort.save(any(Product.class))).thenReturn(savedProduct);

    // When
    Product result = createProductService.create(command);

    // Then
    assertNotNull(result);
    verify(saveProductPort).save(any(Product.class));
  }

  @Test
  void shouldThrowExceptionWhenCommandIsNull() {
    assertThrows(NullPointerException.class, () -> createProductService.create(null));
  }

  @Test
  void shouldCallSaveWithCorrectProduct() {
    // Given
    CreateProductCommand command = new CreateProductCommand("Product", BigDecimal.ONE, 1);
    Product expectedProduct = new Product(ProductId.newId(), "Product", BigDecimal.ONE, 1);
    when(saveProductPort.save(any(Product.class))).thenReturn(expectedProduct);

    // When
    createProductService.create(command);

    // Then
    verify(saveProductPort)
        .save(
            argThat(
                product ->
                    product.getName().equals("Product")
                        && product.getPrice().equals(BigDecimal.ONE)
                        && product.getQuantity() == 1));
  }
}
