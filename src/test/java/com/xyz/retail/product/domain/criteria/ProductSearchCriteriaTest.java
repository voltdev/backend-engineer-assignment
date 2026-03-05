/* Copyright 2026 XYZ Retail */
package com.xyz.retail.product.domain.criteria;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class ProductSearchCriteriaTest {

  @Test
  void shouldCreateProductSearchCriteriaWithName() {
    String name = "test product";
    ProductSearchCriteria criteria = new ProductSearchCriteria(name);
    assertEquals(name, criteria.name());
  }

  @Test
  void shouldAllowNullName() {
    ProductSearchCriteria criteria = new ProductSearchCriteria(null);
    assertNull(criteria.name());
  }

  @Test
  void shouldBeEqualWhenSameName() {
    ProductSearchCriteria criteria1 = new ProductSearchCriteria("test");
    ProductSearchCriteria criteria2 = new ProductSearchCriteria("test");
    assertEquals(criteria1, criteria2);
    assertEquals(criteria1.hashCode(), criteria2.hashCode());
  }

  @Test
  void shouldNotBeEqualWhenDifferentName() {
    ProductSearchCriteria criteria1 = new ProductSearchCriteria("test1");
    ProductSearchCriteria criteria2 = new ProductSearchCriteria("test2");
    assertNotEquals(criteria1, criteria2);
  }
}
