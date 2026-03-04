/* Copyright 2026 XYZ Retail */
package com.xyz.retail.order.presentation.controller;

import java.util.UUID;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import com.xyz.retail.order.application.port.in.OrderSearchUseCase;
import com.xyz.retail.order.application.port.in.PlaceOrderUseCase;
import com.xyz.retail.order.application.port.in.query.OrderSearchQuery;
import com.xyz.retail.order.domain.entity.Order;
import com.xyz.retail.order.domain.exception.OrderException;
import com.xyz.retail.order.presentation.dto.OrderResponseDto;
import com.xyz.retail.order.presentation.dto.PlaceOrderRequestDto;
import com.xyz.retail.order.presentation.mapper.OrderApiMapper;

import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/v1/orders")
public class OrderController {

  private final PlaceOrderUseCase placeOrderUseCase;
  private final OrderSearchUseCase orderSearchUseCase;

  public OrderController(
      PlaceOrderUseCase placeOrderUseCase, OrderSearchUseCase orderSearchUseCase) {
    this.placeOrderUseCase = placeOrderUseCase;
    this.orderSearchUseCase = orderSearchUseCase;
  }

  @Operation(summary = "Place a new order from cart")
  @PostMapping
  public OrderResponseDto placeOrder(
      @RequestHeader("X-User-ID") String userId, @Valid @RequestBody PlaceOrderRequestDto dto) {
    try {
      Order order = placeOrderUseCase.placeOrder(OrderApiMapper.toCommand(userId, dto));
      return OrderApiMapper.toResponse(order);
    } catch (OrderException e) {
      throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
    }
  }

  @Operation(summary = "Search order by ID")
  @GetMapping("/{orderId}")
  public OrderResponseDto searchOrderById(@PathVariable UUID orderId) {
    return orderSearchUseCase
        .findOrderById(new OrderSearchQuery(orderId))
        .map(OrderApiMapper::toResponse)
        .orElseThrow(
            () ->
                new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "Order not found with ID: " + orderId));
  }
}
