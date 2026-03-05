/* Copyright 2026 XYZ Retail */
package com.xyz.retail.product.service.domain.entity;

import static org.junit.jupiter.api.Assertions.*;

import java.math.BigDecimal;

import org.junit.jupiter.api.Test;

import com.xyz.retail.product.domain.valueobject.ProductId;

class ProductTest {

  @Test
  void shouldCreateProductWithValidData() {
    ProductId id = ProductId.newId();
    String name = "Test Product";
    BigDecimal price = BigDecimal.valueOf(10.99);
    int quantity = 5;

    Product product = new Product(id, name, price, quantity);

    assertEquals(id, product.getId());
    assertEquals(name, product.getName());
    assertEquals(price, product.getPrice());
    assertEquals(quantity, product.getQuantity());
  }

  @Test
  void shouldThrowExceptionForNullName() {
    ProductId id = ProductId.newId();
    assertThrows(IllegalArgumentException.class, () -> new Product(id, null, BigDecimal.ONE, 1));
  }

  @Test
  void shouldThrowExceptionForBlankName() {
    ProductId id = ProductId.newId();
    assertThrows(IllegalArgumentException.class, () -> new Product(id, "", BigDecimal.ONE, 1));
  }

  @Test
  void shouldThrowExceptionForNullPrice() {
    ProductId id = ProductId.newId();
    assertThrows(IllegalArgumentException.class, () -> new Product(id, "name", null, 1));
  }

  @Test
  void shouldThrowExceptionForZeroPrice() {
    ProductId id = ProductId.newId();
    assertThrows(IllegalArgumentException.class, () -> new Product(id, "name", BigDecimal.ZERO, 1));
  }

  @Test
  void shouldThrowExceptionForNegativePrice() {
    ProductId id = ProductId.newId();
    assertThrows(
        IllegalArgumentException.class, () -> new Product(id, "name", BigDecimal.valueOf(-1), 1));
  }

  @Test
  void shouldThrowExceptionForNegativeQuantity() {
    ProductId id = ProductId.newId();
    assertThrows(IllegalArgumentException.class, () -> new Product(id, "name", BigDecimal.ONE, -1));
  }

  @Test
  void shouldReturnLowStockWhenQuantityLessThan10() {
    Product product = new Product(ProductId.newId(), "name", BigDecimal.ONE, 5);
    assertTrue(product.isLowStock());
  }

  @Test
  void shouldNotReturnLowStockWhenQuantity10OrMore() {
    Product product = new Product(ProductId.newId(), "name", BigDecimal.ONE, 10);
    assertFalse(product.isLowStock());
  }

  @Test
  void shouldReduceStock() {
    Product product = new Product(ProductId.newId(), "name", BigDecimal.ONE, 10);
    Product reduced = product.reduceStock(3);
    assertEquals(7, reduced.getQuantity());
    assertEquals(product.getId(), reduced.getId());
  }

  @Test
  void shouldThrowExceptionWhenReducingMoreThanAvailable() {
    Product product = new Product(ProductId.newId(), "name", BigDecimal.ONE, 5);
    assertThrows(IllegalArgumentException.class, () -> product.reduceStock(10));
  }

  @Test
  void shouldThrowExceptionForZeroReduction() {
    Product product = new Product(ProductId.newId(), "name", BigDecimal.ONE, 5);
    assertThrows(IllegalArgumentException.class, () -> product.reduceStock(0));
  }

  @Test
  void shouldIncreaseStock() {
    Product product = new Product(ProductId.newId(), "name", BigDecimal.ONE, 5);
    Product increased = product.increaseStock(3);
    assertEquals(8, increased.getQuantity());
  }

  @Test
  void shouldThrowExceptionForZeroIncrease() {
    Product product = new Product(ProductId.newId(), "name", BigDecimal.ONE, 5);
    assertThrows(IllegalArgumentException.class, () -> product.increaseStock(0));
  }

  @Test
  void shouldBeEqualWhenSameId() {
    ProductId id = ProductId.newId();
    Product p1 = new Product(id, "name", BigDecimal.ONE, 1);
    Product p2 = new Product(id, "other", BigDecimal.TEN, 2);
    assertEquals(p1, p2);
    assertEquals(p1.hashCode(), p2.hashCode());
  }

  @Test
  void shouldNotBeEqualWhenDifferentId() {
    Product p1 = new Product(ProductId.newId(), "name", BigDecimal.ONE, 1);
    Product p2 = new Product(ProductId.newId(), "name", BigDecimal.ONE, 1);
    assertNotEquals(p1, p2);
  }
}
