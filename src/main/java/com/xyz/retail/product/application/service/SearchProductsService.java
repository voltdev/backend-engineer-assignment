/* Copyright 2026 XYZ Retail */
package com.xyz.retail.product.application.service;

import java.util.List;
import java.util.Objects;

import com.xyz.retail.product.application.port.in.SearchProductsUseCase;
import com.xyz.retail.product.application.port.in.query.SearchProductsQuery;
import com.xyz.retail.product.application.port.out.LoadProductPort;
import com.xyz.retail.product.service.domain.entity.Product;

public class SearchProductsService implements SearchProductsUseCase {

  private final LoadProductPort loadProductPort;

  public SearchProductsService(LoadProductPort loadProductPort) {
    this.loadProductPort = loadProductPort;
  }

  @Override
  public List<Product> search(SearchProductsQuery query) {
    Objects.requireNonNull(query, "Query cannot be null");
    return loadProductPort.loadByName(query.name());
  }
}
