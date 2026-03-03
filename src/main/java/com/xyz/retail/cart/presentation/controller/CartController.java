/* Copyright 2026 XYZ Retail */
package com.xyz.retail.cart.presentation.controller;

import org.springframework.web.bind.annotation.*;

import com.xyz.retail.cart.application.port.in.AddToCartUseCase;
import com.xyz.retail.cart.application.port.in.GetCartUseCase;
import com.xyz.retail.cart.application.port.in.command.AddToCartCommand;
import com.xyz.retail.cart.application.port.in.query.GetCartQuery;
import com.xyz.retail.cart.domain.entity.Cart;
import com.xyz.retail.cart.presentation.dto.AddToCartRequestDto;
import com.xyz.retail.cart.presentation.dto.CartResponseDto;
import com.xyz.retail.cart.presentation.mapper.CartApiMapper;

import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/v1/cart")
public class CartController {

  private final AddToCartUseCase addToCartUseCase;
  private final GetCartUseCase getCartUseCase;

  public CartController(AddToCartUseCase addToCartUseCase, GetCartUseCase getCartUseCase) {
    this.addToCartUseCase = addToCartUseCase;
    this.getCartUseCase = getCartUseCase;
  }

  @Operation(summary = "Add product to cart")
  @PostMapping("/items")
  public CartResponseDto addToCart(
      @RequestHeader("X-User-ID") String userId, @Valid @RequestBody AddToCartRequestDto dto) {
    AddToCartCommand command = CartApiMapper.toCommand(userId, dto);
    Cart updatedCart = addToCartUseCase.addToCart(command);
    return CartApiMapper.toResponse(updatedCart);
  }

  @Operation(summary = "Get user's cart")
  @GetMapping
  public CartResponseDto getCart(@RequestHeader("X-User-ID") String userId) {
    Cart cart = getCartUseCase.getCart(new GetCartQuery(userId));
    return CartApiMapper.toResponse(cart);
  }
}
