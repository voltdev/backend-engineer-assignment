package com.xyz.retail.product.application.port.in;

import com.xyz.retail.product.application.port.in.query.SearchProductsQuery;
import com.xyz.retail.product.service.domain.entity.Product;

import java.util.List;

public interface SearchProductsUseCase {
    List<Product> search(SearchProductsQuery query);
}