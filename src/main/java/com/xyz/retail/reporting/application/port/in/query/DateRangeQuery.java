/* Copyright 2026 XYZ Retail */
package com.xyz.retail.reporting.application.port.in.query;

import java.time.LocalDate;

public record DateRangeQuery(LocalDate startDate, LocalDate endDate) {}
