/* Copyright 2026 XYZ Retail */
package com.xyz.retail.order.application.port.in.command;

public record PlaceOrderCommand(String userId, String customerName, String mobileNumber) {}
