/* Copyright 2026 XYZ Retail */
package com.xyz.retail.reporting.presentation.dto;

import java.time.LocalDate;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;

public record DateRangeRequestDto(
    @NotNull(message = "Start date is required")
        @PastOrPresent(message = "Start date must be in the past or present")
        LocalDate startDate,
    @NotNull(message = "End date is required")
        @PastOrPresent(message = "End date must be in the past or present")
        LocalDate endDate) {}
