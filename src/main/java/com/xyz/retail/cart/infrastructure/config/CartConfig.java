/* Copyright 2026 XYZ Retail */
package com.xyz.retail.cart.infrastructure.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.xyz.retail.cart.application.port.out.DeleteCartPort;
import com.xyz.retail.cart.application.port.out.LoadCartPort;
import com.xyz.retail.cart.application.port.out.SaveCartPort;
import com.xyz.retail.cart.application.service.CartService;
import com.xyz.retail.product.application.port.out.LoadProductPort;

@Configuration
public class CartConfig {

  @Bean
  public CartService cartService(
      LoadCartPort loadCartPort,
      SaveCartPort saveCartPort,
      DeleteCartPort deleteCartPort,
      LoadProductPort loadProductPort) {
    return new CartService(loadCartPort, saveCartPort, loadProductPort, deleteCartPort);
  }
}
