/* Copyright 2026 XYZ Retail */
package com.xyz.retail.reporting.presentation.dto;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;

class DateRangeRequestDtoTest {

  @Test
  void shouldCreateDateRangeRequestDto() {
    LocalDate startDate = LocalDate.of(2026, 1, 1);
    LocalDate endDate = LocalDate.of(2026, 1, 31);

    DateRangeRequestDto dto = new DateRangeRequestDto(startDate, endDate);

    assertEquals(startDate, dto.startDate());
    assertEquals(endDate, dto.endDate());
  }

  @Test
  void shouldAllowNullDates() {
    DateRangeRequestDto dto = new DateRangeRequestDto(null, null);
    assertNull(dto.startDate());
    assertNull(dto.endDate());
  }
}
