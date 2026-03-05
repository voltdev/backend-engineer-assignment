/* Copyright 2026 XYZ Retail */
package com.xyz.retail.reporting.application.port.in.query;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;

class DateRangeQueryTest {

  @Test
  void shouldCreateDateRangeQuery() {
    LocalDate startDate = LocalDate.of(2026, 1, 1);
    LocalDate endDate = LocalDate.of(2026, 1, 31);

    DateRangeQuery query = new DateRangeQuery(startDate, endDate);

    assertEquals(startDate, query.startDate());
    assertEquals(endDate, query.endDate());
  }

  @Test
  void shouldAllowNullDates() {
    DateRangeQuery query = new DateRangeQuery(null, null);
    assertNull(query.startDate());
    assertNull(query.endDate());
  }

  @Test
  void shouldBeEqualWhenSameDates() {
    LocalDate startDate = LocalDate.of(2026, 1, 1);
    LocalDate endDate = LocalDate.of(2026, 1, 31);
    DateRangeQuery q1 = new DateRangeQuery(startDate, endDate);
    DateRangeQuery q2 = new DateRangeQuery(startDate, endDate);
    assertEquals(q1, q2);
  }
}
