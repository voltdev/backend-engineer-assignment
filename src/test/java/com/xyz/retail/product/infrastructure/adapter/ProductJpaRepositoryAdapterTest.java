/* Copyright 2026 XYZ Retail */
package com.xyz.retail.product.infrastructure.adapter;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.xyz.retail.product.domain.valueobject.ProductId;
import com.xyz.retail.product.infrastructure.entity.JpaProductEntity;
import com.xyz.retail.product.infrastructure.repository.SpringDataProductRepository;
import com.xyz.retail.product.service.domain.entity.Product;

class ProductJpaRepositoryAdapterTest {

  @Mock private SpringDataProductRepository repository;

  private ProductJpaRepositoryAdapter adapter;

  @BeforeEach
  void setUp() {
    MockitoAnnotations.openMocks(this);
    adapter = new ProductJpaRepositoryAdapter(repository);
  }

  @Test
  void shouldLoadByName() {
    // Given
    String pattern = "test";
    JpaProductEntity entity =
        new JpaProductEntity(UUID.randomUUID(), "Test Product", BigDecimal.ONE, 1);
    when(repository.findByNameContainingIgnoreCase(pattern)).thenReturn(List.of(entity));

    // When
    List<Product> products = adapter.loadByName(pattern);

    // Then
    assertEquals(1, products.size());
    verify(repository).findByNameContainingIgnoreCase(pattern);
  }

  @Test
  void shouldLoadProductById() {
    // Given
    UUID id = UUID.randomUUID();
    JpaProductEntity entity = new JpaProductEntity(id, "Product", BigDecimal.ONE, 1);
    when(repository.findById(id)).thenReturn(Optional.of(entity));

    // When
    Optional<Product> product = adapter.loadProductById(id);

    // Then
    assertTrue(product.isPresent());
    assertEquals("Product", product.get().getName());
    verify(repository).findById(id);
  }

  @Test
  void shouldReturnEmptyWhenProductNotFound() {
    // Given
    UUID id = UUID.randomUUID();
    when(repository.findById(id)).thenReturn(Optional.empty());

    // When
    Optional<Product> product = adapter.loadProductById(id);

    // Then
    assertFalse(product.isPresent());
  }

  @Test
  void shouldSaveProduct() {
    // Given
    Product product = new Product(ProductId.newId(), "Product", BigDecimal.ONE, 1);
    JpaProductEntity entity =
        new JpaProductEntity(product.getId().value(), "Product", BigDecimal.ONE, 1);
    when(repository.save(any(JpaProductEntity.class))).thenReturn(entity);

    // When
    Product saved = adapter.save(product);

    // Then
    assertNotNull(saved);
    verify(repository).save(any(JpaProductEntity.class));
  }

  @Test
  void shouldFindById() {
    // Given
    ProductId productId = ProductId.newId();
    JpaProductEntity entity = new JpaProductEntity(productId.value(), "Product", BigDecimal.ONE, 1);
    when(repository.findById(productId.value())).thenReturn(Optional.of(entity));

    // When
    Optional<Product> product = adapter.findById(productId);

    // Then
    assertTrue(product.isPresent());
    verify(repository).findById(productId.value());
  }
}
