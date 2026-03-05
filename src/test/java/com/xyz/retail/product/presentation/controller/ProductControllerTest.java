/* Copyright 2026 XYZ Retail */
package com.xyz.retail.product.presentation.controller;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

import java.math.BigDecimal;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.xyz.retail.product.application.port.in.CreateProductUseCase;
import com.xyz.retail.product.application.port.in.SearchProductsUseCase;
import com.xyz.retail.product.domain.valueobject.ProductId;
import com.xyz.retail.product.presentation.dto.ProductRequestDto;
import com.xyz.retail.product.presentation.dto.ProductResponseDto;
import com.xyz.retail.product.service.domain.entity.Product;

class ProductControllerTest {

  @Mock private SearchProductsUseCase searchProductsUseCase;

  @Mock private CreateProductUseCase createProductUseCase;

  private ProductController controller;

  @BeforeEach
  void setUp() {
    MockitoAnnotations.openMocks(this);
    controller = new ProductController(searchProductsUseCase, createProductUseCase);
  }

  @Test
  void shouldSearchProducts() {
    // Given
    String name = "test";
    Product product = new Product(ProductId.newId(), "Test Product", BigDecimal.ONE, 1);
    when(searchProductsUseCase.search(any())).thenReturn(List.of(product));

    // When
    List<ProductResponseDto> results = controller.searchProducts(name);

    // Then
    assertEquals(1, results.size());
    verify(searchProductsUseCase).search(any());
  }

  @Test
  void shouldCreateProduct() {
    // Given
    ProductRequestDto dto = new ProductRequestDto("Test", BigDecimal.ONE, 1);
    Product product = new Product(ProductId.newId(), "Test", BigDecimal.ONE, 1);
    when(createProductUseCase.create(any())).thenReturn(product);

    // When
    ProductResponseDto result = controller.createProduct(dto);

    // Then
    assertNotNull(result);
    verify(createProductUseCase).create(any());
  }
}
