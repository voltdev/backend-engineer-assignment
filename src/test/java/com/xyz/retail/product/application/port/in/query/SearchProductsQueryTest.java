/* Copyright 2026 XYZ Retail */
package com.xyz.retail.product.application.port.in.query;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class SearchProductsQueryTest {

  @Test
  void shouldCreateQueryWithName() {
    String name = "search term";
    SearchProductsQuery query = new SearchProductsQuery(name);
    assertEquals(name, query.name());
  }

  @Test
  void shouldAllowNullName() {
    SearchProductsQuery query = new SearchProductsQuery(null);
    assertNull(query.name());
  }

  @Test
  void shouldBeEqualWhenSameName() {
    SearchProductsQuery q1 = new SearchProductsQuery("test");
    SearchProductsQuery q2 = new SearchProductsQuery("test");
    assertEquals(q1, q2);
    assertEquals(q1.hashCode(), q2.hashCode());
  }

  @Test
  void shouldNotBeEqualWhenDifferentName() {
    SearchProductsQuery q1 = new SearchProductsQuery("test1");
    SearchProductsQuery q2 = new SearchProductsQuery("test2");
    assertNotEquals(q1, q2);
  }
}
