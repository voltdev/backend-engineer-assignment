/* Copyright 2026 XYZ Retail */
package com.xyz.retail.order.presentation.dto;

import jakarta.validation.constraints.NotBlank;

public record PlaceOrderRequestDto(
    @NotBlank(message = "Customer name is required") String customerName,
    @NotBlank(message = "Mobile number is required") String mobileNumber) {}
