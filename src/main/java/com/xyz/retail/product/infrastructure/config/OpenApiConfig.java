/* Copyright 2026 XYZ Retail */
package com.xyz.retail.product.infrastructure.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;

@Configuration
public class OpenApiConfig {

  @Bean
  public OpenAPI productServiceOpenApi() {
    return new OpenAPI()
        .info(
            new Info()
                .title("Product Service API")
                .version("1.0.0")
                .description("API Documentation for Product Microservice"));
  }
}
