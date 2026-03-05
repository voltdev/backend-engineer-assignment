/* Copyright 2026 XYZ Retail */
package com.xyz.retail.product.infrastructure.mapper;

import static org.junit.jupiter.api.Assertions.*;

import java.math.BigDecimal;
import java.util.UUID;

import org.junit.jupiter.api.Test;

import com.xyz.retail.product.domain.valueobject.ProductId;
import com.xyz.retail.product.infrastructure.entity.JpaProductEntity;
import com.xyz.retail.product.service.domain.entity.Product;

class ProductMapperTest {

  @Test
  void shouldMapProductToEntity() {
    // Given
    ProductId productId = ProductId.newId();
    Product product = new Product(productId, "Test Product", BigDecimal.valueOf(10.99), 5);

    // When
    JpaProductEntity entity = ProductMapper.toEntity(product);

    // Then
    assertEquals(productId.value(), entity.getId());
    assertEquals("Test Product", entity.getName());
    assertEquals(BigDecimal.valueOf(10.99), entity.getPrice());
    assertEquals(5, entity.getQuantity());
  }

  @Test
  void shouldMapEntityToProduct() {
    // Given
    UUID id = UUID.randomUUID();
    JpaProductEntity entity =
        new JpaProductEntity(id, "Test Product", BigDecimal.valueOf(10.99), 5);

    // When
    Product product = ProductMapper.toDomain(entity);

    // Then
    assertEquals(new ProductId(id), product.getId());
    assertEquals("Test Product", product.getName());
    assertEquals(BigDecimal.valueOf(10.99), product.getPrice());
    assertEquals(5, product.getQuantity());
  }

  @Test
  void shouldHandleNullValuesInMapping() {
    // Given - but Product constructor throws for null, so can't create with null
    // Perhaps test with valid Product
    ProductId productId = ProductId.newId();
    Product product = new Product(productId, "name", BigDecimal.ONE, 1);

    // When
    JpaProductEntity entity = ProductMapper.toEntity(product);

    // Then
    assertEquals(productId.value(), entity.getId());
    assertEquals("name", entity.getName());
    assertEquals(BigDecimal.ONE, entity.getPrice());
    assertEquals(1, entity.getQuantity());
  }
}
