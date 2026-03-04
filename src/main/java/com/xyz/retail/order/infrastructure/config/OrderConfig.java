/* Copyright 2026 XYZ Retail */
package com.xyz.retail.order.infrastructure.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.xyz.retail.cart.application.port.in.ClearCartUseCase;
import com.xyz.retail.cart.application.port.in.GetCartUseCase;
import com.xyz.retail.order.application.port.out.LoadUserPort;
import com.xyz.retail.order.application.port.out.SaveOrderPort;
import com.xyz.retail.order.application.service.OrderService;
import com.xyz.retail.product.application.port.out.LoadProductPort;

@Configuration
public class OrderConfig {

  @Bean
  public OrderService orderService(
      GetCartUseCase getCartUseCase,
      ClearCartUseCase clearCartUseCase,
      SaveOrderPort saveOrderPort,
      LoadUserPort loadUserPort,
      LoadProductPort loadProductPort) {
    return new OrderService(
        getCartUseCase, clearCartUseCase, saveOrderPort, loadUserPort, loadProductPort);
  }
}
