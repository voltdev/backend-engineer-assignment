/* Copyright 2026 XYZ Retail */
package com.xyz.retail.product.application.port.in;

import com.xyz.retail.product.application.port.in.command.CreateProductCommand;
import com.xyz.retail.product.service.domain.entity.Product;

public interface CreateProductUseCase {
  Product create(CreateProductCommand command);
}
