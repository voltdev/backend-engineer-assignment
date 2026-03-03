/* Copyright 2026 XYZ Retail */
package com.xyz.retail.product.infrastructure.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.xyz.retail.product.application.port.in.CreateProductUseCase;
import com.xyz.retail.product.application.port.in.SearchProductsUseCase;
import com.xyz.retail.product.application.port.out.LoadProductPort;
import com.xyz.retail.product.application.port.out.SaveProductPort;
import com.xyz.retail.product.application.service.CreateProductService;
import com.xyz.retail.product.application.service.SearchProductsService;

@Configuration
public class ApplicationConfig {

  @Bean
  public SearchProductsUseCase searchProductsUseCase(LoadProductPort loadPort) {
    return new SearchProductsService(loadPort);
  }

  @Bean
  public CreateProductUseCase createProductUseCase(SaveProductPort savePort) {
    return new CreateProductService(savePort);
  }
}
