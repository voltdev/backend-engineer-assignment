/* Copyright 2026 XYZ Retail */
package com.xyz.retail.product.application.port.in;

import java.util.List;

import com.xyz.retail.product.application.port.in.query.SearchProductsQuery;
import com.xyz.retail.product.service.domain.entity.Product;

public interface SearchProductsUseCase {
  List<Product> search(SearchProductsQuery query);
}
