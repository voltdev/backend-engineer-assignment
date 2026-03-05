/* Copyright 2026 XYZ Retail */
package com.xyz.retail.product.application.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.xyz.retail.product.application.port.in.query.SearchProductsQuery;
import com.xyz.retail.product.application.port.out.LoadProductPort;
import com.xyz.retail.product.service.domain.entity.Product;

class SearchProductsServiceTest {

  @Mock private LoadProductPort loadProductPort;

  private SearchProductsService searchProductsService;

  @BeforeEach
  void setUp() {
    MockitoAnnotations.openMocks(this);
    searchProductsService = new SearchProductsService(loadProductPort);
  }

  @Test
  void shouldSearchProductsSuccessfully() {
    // Given
    SearchProductsQuery query = new SearchProductsQuery("test");
    List<Product> products = List.of(); // empty list
    when(loadProductPort.loadByName("test")).thenReturn(products);

    // When
    List<Product> result = searchProductsService.search(query);

    // Then
    assertEquals(products, result);
    verify(loadProductPort).loadByName("test");
  }

  @Test
  void shouldThrowExceptionWhenQueryIsNull() {
    assertThrows(NullPointerException.class, () -> searchProductsService.search(null));
  }

  @Test
  void shouldCallLoadWithNullName() {
    // Given
    SearchProductsQuery query = new SearchProductsQuery(null);
    when(loadProductPort.loadByName(null)).thenReturn(List.of());

    // When
    searchProductsService.search(query);

    // Then
    verify(loadProductPort).loadByName(null);
  }
}
