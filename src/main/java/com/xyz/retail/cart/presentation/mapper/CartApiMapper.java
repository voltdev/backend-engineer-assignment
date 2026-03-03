/* Copyright 2026 XYZ Retail */
package com.xyz.retail.cart.presentation.mapper;

import java.util.List;
import java.util.stream.Collectors;

import com.xyz.retail.cart.application.port.in.command.AddToCartCommand;
import com.xyz.retail.cart.domain.entity.Cart;
import com.xyz.retail.cart.domain.entity.CartItem;
import com.xyz.retail.cart.presentation.dto.AddToCartRequestDto;
import com.xyz.retail.cart.presentation.dto.CartResponseDto;

public class CartApiMapper {

  public static AddToCartCommand toCommand(String userId, AddToCartRequestDto dto) {
    return new AddToCartCommand(
        userId, dto.productId(), dto.productName(), dto.productPrice(), dto.quantity());
  }

  public static CartResponseDto toResponse(Cart cart) {
    List<CartResponseDto.CartItemDto> itemDtos =
        cart.getItems().stream().map(CartApiMapper::toItemDto).collect(Collectors.toList());

    return new CartResponseDto(
        cart.getId().value(), cart.getUserId().value(), itemDtos, cart.getTotalPrice());
  }

  private static CartResponseDto.CartItemDto toItemDto(CartItem item) {
    return new CartResponseDto.CartItemDto(
        item.getId(),
        item.getProductId(),
        item.getProductName(),
        item.getProductPrice(),
        item.getQuantity(),
        item.getSubtotal());
  }
}
