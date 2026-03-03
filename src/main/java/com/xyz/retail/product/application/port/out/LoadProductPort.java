/* Copyright 2026 XYZ Retail */
package com.xyz.retail.product.application.port.out;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import com.xyz.retail.product.service.domain.entity.Product;

public interface LoadProductPort {
  List<Product> loadByName(String namePattern);

  Optional<Product> loadProductById(UUID productId);
}
