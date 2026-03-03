/* Copyright 2026 XYZ Retail */
package com.xyz.retail.product.domain.repository;

import java.util.List;
import java.util.Optional;

import com.xyz.retail.product.domain.valueobject.ProductId;
import com.xyz.retail.product.service.domain.entity.Product;

public interface ProductRepository {

  Optional<Product> findById(ProductId id);

  List<Product> searchByName(String nameQuery);

  Product save(Product product);
}
