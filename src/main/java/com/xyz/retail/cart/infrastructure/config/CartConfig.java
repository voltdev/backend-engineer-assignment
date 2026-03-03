package com.xyz.retail.cart.infrastructure.config;

import com.xyz.retail.cart.application.port.out.LoadCartPort;
import com.xyz.retail.cart.application.port.out.SaveCartPort;
import com.xyz.retail.cart.application.service.CartService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CartConfig {

    @Bean
    public CartService cartService(LoadCartPort loadCartPort, SaveCartPort saveCartPort) {
        return new CartService(loadCartPort, saveCartPort);
    }
}