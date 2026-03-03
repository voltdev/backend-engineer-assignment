/* Copyright 2026 XYZ Retail */
package com.xyz.retail.product.application.port.out;

import com.xyz.retail.product.service.domain.entity.Product;

public interface SaveProductPort {
  Product save(Product product);
}
