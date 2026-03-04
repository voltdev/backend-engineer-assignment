/* Copyright 2026 XYZ Retail */
package com.xyz.retail.order.presentation.mapper;

import java.util.List;
import java.util.stream.Collectors;

import com.xyz.retail.order.application.port.in.command.PlaceOrderCommand;
import com.xyz.retail.order.domain.entity.Order;
import com.xyz.retail.order.domain.entity.OrderItem;
import com.xyz.retail.order.presentation.dto.OrderResponseDto;
import com.xyz.retail.order.presentation.dto.PlaceOrderRequestDto;

public class OrderApiMapper {

  public static PlaceOrderCommand toCommand(String userId, PlaceOrderRequestDto dto) {
    return new PlaceOrderCommand(userId, dto.customerName(), dto.mobileNumber());
  }

  public static OrderResponseDto toResponse(Order order) {
    List<OrderResponseDto.OrderItemDto> itemDtos =
        order.getItems().stream().map(OrderApiMapper::toItemDto).collect(Collectors.toList());

    return new OrderResponseDto(
        order.getId().value(),
        order.getUserId(),
        order.getCustomerName(),
        order.getMobileNumber(),
        itemDtos,
        order.getTotalAmount(),
        order.getCreatedAt(),
        order.getStatus().name());
  }

  private static OrderResponseDto.OrderItemDto toItemDto(OrderItem item) {
    return new OrderResponseDto.OrderItemDto(
        item.getProductId(),
        item.getProductName(),
        item.getProductPrice(),
        item.getQuantity(),
        item.getSubtotal());
  }
}
