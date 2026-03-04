/* Copyright 2026 XYZ Retail */
package com.xyz.retail.reporting.presentation.dto;

import java.math.BigDecimal;
import java.time.LocalDate;

public record DailySalesReportDto(LocalDate date, BigDecimal totalSales, int orderCount) {}
